package io.skyhive.veneer.employee.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.employee.es.CandidateWithScore;
import io.skyhive.veneer.employee.es.EmployeeSearchRequest;
import io.skyhive.veneer.employee.es.EmployeeSearchResponse;
import io.skyhive.veneer.employee.mapper.EmployeeMapper;
import io.skyhive.veneer.models.es.MatchingSkill;
import io.skyhive.veneer.models.es.candidate.Candidate;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author krishna
 * @created 20/12/21
 * @project skyhive-veeneer
 */
@Service
@Slf4j
public class CandidateEsServiceImpl implements CandidateService {
	private final CandidateEsRepository candidateRepository;
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;
	private final ObjectMapper objectMapper;
	private final EmployeeMapper employeeMapper;

	@Autowired
	public CandidateEsServiceImpl(final CandidateEsRepository candidateRepository,
			final ElasticsearchRestTemplate elasticsearchRestTemplate, final ObjectMapper objectMapper,
			final EmployeeMapper employeeMapper) {
		this.candidateRepository = candidateRepository;
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
		this.objectMapper = objectMapper;
		this.employeeMapper = employeeMapper;
	}

	@PostConstruct
	public void init() {
		IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Candidate.class);
		indexOperations.putMapping(indexOperations.createMapping());
		indexOperations.refresh();
	}

	public Candidate save(Candidate can) {
		log.info("Saving candidate object");
		return candidateRepository.save(can);
	}

	public boolean existsById(String id, String enterpriseId) throws NotFoundException {
		Candidate candidate = findById(id, enterpriseId);
		return !ObjectUtils.isEmpty(candidate);
	}

	public Candidate findById(String id, String enterpriseId) throws NotFoundException {
		Optional<Candidate> candidateOptional = candidateRepository.findById(id);
		if (candidateOptional.isPresent()) {
			Candidate candidate = candidateOptional.get();
			if (enterpriseId.equals(candidate.getEnterpriseId())) {
				return candidate;
			}
		}
		throw new NotFoundException("Given Id not found");
	}

	public void delete(Candidate can) {
		candidateRepository.delete(can);
	}

	public EmployeeSearchResponse searchCandidates(EmployeeSearchRequest employeeSearchRequest, int pageNumber,
			int pageSize, String enterpriseId) throws JsonProcessingException {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("enterpriseId", enterpriseId));
		boolQueryBuilder = buildQueryBuilderbyJobTitles(employeeSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildCountryQueryBuilder(employeeSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByDegree(employeeSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByDesiredJobTitles(employeeSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByCompanyName(employeeSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByHiredDates(employeeSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByUniversity(employeeSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByLocation(employeeSearchRequest, boolQueryBuilder);


		QueryBuilder queryBuilder = boolQueryBuilder;
		if (!CollectionUtils.isEmpty(employeeSearchRequest.getSkills())) {
			queryBuilder = buildSkillsQuery(employeeSearchRequest, boolQueryBuilder);
		}

		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(queryBuilder)
				.build();
		log.info("Query =" + searchQuery.getQuery());
		SearchHits<Candidate> response = elasticsearchRestTemplate.search(searchQuery, Candidate.class);
		List<CandidateWithScore> candidateWithScoreList = new LinkedList<>();

		for (SearchHit<Candidate> candidateSearchHits : response.getSearchHits()) {
			CandidateWithScore candidateWithScore = employeeMapper
					.toCandidateWithScore(candidateSearchHits.getContent());
			candidateWithScore.setQuality(candidateSearchHits.getScore());
			log.info(objectMapper.writeValueAsString(candidateWithScore));
			candidateWithScoreList.add(candidateWithScore);
		}
		return new EmployeeSearchResponse(response.getTotalHits(), candidateWithScoreList);
	}

	private BoolQueryBuilder buildCountryQueryBuilder(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(employeeSearchRequest.getCountry())) {
			boolQueryBuilder = boolQueryBuilder
					.must(QueryBuilders.matchQuery("location.country", employeeSearchRequest.getCountry()));
		}
		return boolQueryBuilder;
	}

	private QueryBuilder buildSkillsQuery(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		QueryBuilder queryBuilder;
		Map<String, Map> searchSkills = new HashMap<>();
		int skillValue = 0;
		for (MatchingSkill matchingSkill : employeeSearchRequest.getSkills()) {
			searchSkills.put(matchingSkill.getId(), objectMapper.convertValue(matchingSkill, Map.class));
			skillValue += matchingSkill.getComplexity() * matchingSkill.getRequired().getScore();
		}

		skillValue = 100 / skillValue;
		Map<String, Object> params = new HashMap<>();
		params.put("searchSkills", searchSkills);
		params.put("skillValue", skillValue);
		String scriptString = "int getIntValue(def level){\n"
				+ "              if('Intermediate'.equalsIgnoreCase(level)) return 2;\n"
				+ "              else if ('Entry'.equalsIgnoreCase(level)) return 1;\n"
				+ "              else return 3;\n" + "            }\n"
				+ "            int getRequiredValue(def required){\n"
				+ "              if('Optional'.equalsIgnoreCase(required)) return 1;\n"
				+ "              else return 2;\n" + "            }\n" + "            int score=0;\n"
				+ "            def skills = params._source.skills;\n" + "            if(skills == null){return 0;}\n"
				+ "            def skillMap = new HashMap();\n" + "            for(def skill: skills){\n"
				+ "              skillMap.put(skill.id, skill);\n" + "            }\n"
				+ "            for(def id : params.searchSkills.keySet()){\n"
				+ "              def skillRequired = params.searchSkills.get(id);\n"
				+ "              def currentValue = params.skillValue * skillRequired.complexity *getRequiredValue(skillRequired.required);\n"
				+ "              if(skillMap.containsKey(id)){\n" + "               score = score+currentValue;\n"
				+ "              } else {\n" + "                def skillGiven = skillMap.get(id);\n"
				+ "                if(skillGiven != null && getIntValue"
				+ "(skillGiven.level)<getIntValue(skillRequired.level)){\n"
				+ "                  score = score - (currentValue*(getIntValue(skillRequired.level)-getIntValue(skillGiven.level))/10);\n"
				+ "                }\n" + "              }\n" + "            }"
				+ "           def scoreDouble = score/100.0;\n" + "            if(scoreDouble > 0.5){\n"
				+ "              scoreDouble = (1.061 / (1 + Math.pow(Math.E, -7 * (scoreDouble - 0.5)))) - 0.03;\n"
				+ "            }\n" + "            return (int)(scoreDouble*100);";
		Script script = new Script(ScriptType.INLINE, Script.DEFAULT_SCRIPT_LANG, scriptString, params);
		queryBuilder = QueryBuilders.scriptScoreQuery(boolQueryBuilder, script)
				.setMinScore(employeeSearchRequest.getMinMatchPercentage());
		return queryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByCompanyName(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(employeeSearchRequest.getCompanyDetails())) {
			BoolQueryBuilder innerQuery = QueryBuilders.boolQuery();
			innerQuery.must(QueryBuilders.matchQuery("experience.company",
					employeeSearchRequest.getCompanyDetails().getName()));
			if (!ObjectUtils.isEmpty(employeeSearchRequest.getCompanyDetails().getCurrent())) {
				innerQuery.must(QueryBuilders.matchQuery("experience.current",
						employeeSearchRequest.getCompanyDetails().getCurrent()));
			}
			boolQueryBuilder = boolQueryBuilder
					.must(QueryBuilders.nestedQuery("experience", innerQuery, ScoreMode.None));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByDesiredJobTitles(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!CollectionUtils.isEmpty(employeeSearchRequest.getDesiredJobTitles())) {
			boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.nestedQuery("desiredJobTitles",
					QueryBuilders.termsQuery("desiredJobTitles.title", employeeSearchRequest.getDesiredJobTitles()),
					ScoreMode.None));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByDegree(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(employeeSearchRequest.getDegree())) {
			boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.nestedQuery("studies",
					QueryBuilders.boolQuery()
							.must(QueryBuilders.matchQuery("studies.degree.title", employeeSearchRequest.getDegree())),
					ScoreMode.None));

		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByLocation(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(employeeSearchRequest.getLocation())) {
			boolQueryBuilder = boolQueryBuilder.filter(QueryBuilders.geoDistanceQuery("location" + ".location")
					.point(employeeSearchRequest.getLocation().getLocation().getLat(),
							employeeSearchRequest.getLocation().getLocation().getLon())
					.distance(employeeSearchRequest.getRadius(), DistanceUnit.KILOMETERS));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByUniversity(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(employeeSearchRequest.getUniversity())) {

			boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.nestedQuery("studies",
					QueryBuilders.boolQuery()
							.must(QueryBuilders.matchQuery("studies.institution.title", employeeSearchRequest.getUniversity())),
					ScoreMode.None));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByHiredDates(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(employeeSearchRequest.getHiredFrom())
				|| !ObjectUtils.isEmpty(employeeSearchRequest.getHiredTo())) {
			boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.rangeQuery("dateStarted")
					.from(employeeSearchRequest.getHiredFromAsString()).to(employeeSearchRequest.getHiredToAsString()));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderbyJobTitles(EmployeeSearchRequest employeeSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!CollectionUtils.isEmpty(employeeSearchRequest.getJobTitles())) {
			boolQueryBuilder = boolQueryBuilder
					.must(QueryBuilders.termsQuery("jobTitle.title", employeeSearchRequest.getJobTitles()));
		}
		return boolQueryBuilder;
	}
}
