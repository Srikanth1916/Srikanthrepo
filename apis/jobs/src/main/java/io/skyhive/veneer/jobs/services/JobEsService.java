package io.skyhive.veneer.jobs.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.skyhive.veneer.jobs.es.JobSearchRequest;
import io.skyhive.veneer.jobs.es.JobSearchResponse;
import io.skyhive.veneer.jobs.es.JobWithScore;
import io.skyhive.veneer.jobs.mapper.JobMapper;
import io.skyhive.veneer.models.es.MatchingSkill;
import io.skyhive.veneer.models.es.job.Job;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Service
@Slf4j
public class JobEsService {
	private final JobEsRepository jobEsRepository;
	private final ObjectMapper objectMapper;
	private final JobMapper jobMapper;
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Autowired
	public JobEsService(JobEsRepository jobEsRepository, ObjectMapper objectMapper, JobMapper jobMapper,
			ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.jobEsRepository = jobEsRepository;
		this.objectMapper = objectMapper;
		this.jobMapper = jobMapper;
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	@PostConstruct
	public void init() {
		IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Job.class);
		indexOperations.putMapping(indexOperations.createMapping());
		indexOperations.refresh();
	}

	public Job save(Job job) {
		return jobEsRepository.save(job);
	}

	public void delete(Job job) {
		jobEsRepository.delete(job);
	}

	public JobSearchResponse searchJobs(JobSearchRequest jobSearchRequest, int pageNumber, int pageSize,
			String enterpriseId) throws JsonProcessingException {
		log.info("Query provided : {}", objectMapper.writeValueAsString(jobSearchRequest));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("enterpriseId", enterpriseId));
		boolQueryBuilder = buildCountryQueryBuilder(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderbyJobTitles(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildWorkTypeQueryBuilder(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByPostedDates(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByMinPayRate(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByPayType(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildJobTypeQueryBuilder(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildJobStateQueryBuilder(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildJobTravelQueryBuilder(jobSearchRequest, boolQueryBuilder);
		boolQueryBuilder = buildQueryBuilderByLocation(jobSearchRequest, boolQueryBuilder);
		QueryBuilder queryBuilder = boolQueryBuilder;

		if (!CollectionUtils.isEmpty(jobSearchRequest.getSkills())) {
			queryBuilder = buildSkillsQuery(jobSearchRequest, boolQueryBuilder);
		}
		SortBuilder sortBuilder = buildSortBuilder(jobSearchRequest);

		NativeSearchQuery searchQuery =
				new NativeSearchQueryBuilder().withPageable(pageable).withQuery(queryBuilder)
						.withSort(sortBuilder).withTrackScores(true)
				.build();
		log.info("Query =" + searchQuery.getQuery());
		log.info("Sort ="+ searchQuery.getElasticsearchSorts());
		SearchHits<Job> response =
				elasticsearchRestTemplate.search(searchQuery, Job.class);
		List<JobWithScore> jobWithScoreList = new LinkedList<>();
		for (SearchHit<Job> jobSearchHit : response.getSearchHits()) {
			JobWithScore jobWithScore = jobMapper.toJobWithScore(jobSearchHit.getContent());
			jobWithScore.setQuality((double) jobSearchHit.getScore());
			jobWithScoreList.add(jobWithScore);
		}
		return new JobSearchResponse(response.getTotalHits(), jobWithScoreList);
	}

	private BoolQueryBuilder buildJobTravelQueryBuilder(JobSearchRequest jobSearchRequest,
												   BoolQueryBuilder boolQueryBuilder) {
		if(!ObjectUtils.isEmpty(jobSearchRequest.getTravel())) {
			boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.matchQuery("travel",
					jobSearchRequest.getTravel().name()));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildJobStateQueryBuilder(JobSearchRequest jobSearchRequest, BoolQueryBuilder boolQueryBuilder) {
		if(!ObjectUtils.isEmpty(jobSearchRequest.getState())) {
			boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.matchQuery("status.state",
					jobSearchRequest.getState().name()));
		}
		return boolQueryBuilder;
	}

	private SortBuilder buildSortBuilder(JobSearchRequest jobSearchRequest) {
		switch (jobSearchRequest.getSortBy()) {
			case location:
				return !ObjectUtils.isEmpty(jobSearchRequest.getLocation()) ? SortBuilders.geoDistanceSort("locationTime" +
								".location.location",
						jobSearchRequest.getLocation().getLocation().getLat(), jobSearchRequest.getLocation().getLocation().getLon()) : SortBuilders.scoreSort();
			case startDate:
				return SortBuilders.fieldSort("locationTime.startDate").order(SortOrder.DESC);
			case quality:
			default: return SortBuilders.scoreSort();
		}
	}

	private BoolQueryBuilder buildQueryBuilderByPayType(JobSearchRequest jobSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(jobSearchRequest.getPayType())) {
			boolQueryBuilder = boolQueryBuilder
					.must(QueryBuilders.matchQuery("compensation.type", jobSearchRequest.getPayType().name()));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByMinPayRate(JobSearchRequest jobSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(jobSearchRequest.getMinPayRate())) {
			boolQueryBuilder = boolQueryBuilder.must(
					QueryBuilders.rangeQuery("compensation.payRate").from(jobSearchRequest.getMinPayRate(), true));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByPostedDates(JobSearchRequest jobSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(jobSearchRequest.getToPostedDate())
				|| !ObjectUtils.isEmpty(jobSearchRequest.getFromPostedDate())) {
			boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.rangeQuery("locationTime.startDate")
					.from(jobSearchRequest.getFromPostedDateAsString()).to(jobSearchRequest.getToPostedDateAsString()));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildWorkTypeQueryBuilder(JobSearchRequest jobSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(jobSearchRequest.getWorkType())) {
			boolQueryBuilder = boolQueryBuilder
					.must(QueryBuilders.termQuery("locationTime.workType", jobSearchRequest.getWorkType().name()));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildCountryQueryBuilder(JobSearchRequest jobSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(jobSearchRequest.getCountry())) {
			boolQueryBuilder = boolQueryBuilder
					.must(QueryBuilders.matchQuery("country", jobSearchRequest.getCountry()));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderbyJobTitles(JobSearchRequest jobSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!CollectionUtils.isEmpty(jobSearchRequest.getJobTitles())) {
			boolQueryBuilder = boolQueryBuilder
					.must(QueryBuilders.termsQuery("title", jobSearchRequest.getJobTitles()));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildQueryBuilderByLocation(JobSearchRequest jobSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(jobSearchRequest.getLocation())) {
			boolQueryBuilder = boolQueryBuilder.filter(QueryBuilders.geoDistanceQuery("locationTime.location.location")
					.point(jobSearchRequest.getLocation().getLocation().getLat(),
							jobSearchRequest.getLocation().getLocation().getLon())
					.distance(jobSearchRequest.getRadius(), DistanceUnit.KILOMETERS));
		}
		return boolQueryBuilder;
	}

	private BoolQueryBuilder buildJobTypeQueryBuilder(JobSearchRequest jobSearchRequest,
			BoolQueryBuilder boolQueryBuilder) {
		if (!ObjectUtils.isEmpty(jobSearchRequest.getType())) {
			boolQueryBuilder = boolQueryBuilder
					.must(QueryBuilders.termQuery("type", jobSearchRequest.getType().name()));
		}
		return boolQueryBuilder;
	}

	private QueryBuilder buildSkillsQuery(JobSearchRequest jobSearchRequest, BoolQueryBuilder boolQueryBuilder) {
		QueryBuilder queryBuilder;
		Map<String, Map> searchSkills = new HashMap<>();
		int skillValue = 0;
		for (MatchingSkill matchingSkill : jobSearchRequest.getSkills()) {
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
				+ "              else return 2;\n" + "            }\n" + "            int score=100;\n"
				+ "            def skills = params._source.skills;\n" + "            if(skills == null){return 0;}\n"
				+ "            def skillMap = new HashMap();\n" + "            for(def skill: skills){\n"
				+ "              skillMap.put(skill.id, skill);\n" + "            }\n"
				+ "            for(def id : params.searchSkills.keySet()){\n"
				+ "              def skillRequired = params.searchSkills.get(id);\n"
				+ "              def currentValue = params.skillValue * skillRequired.complexity *getRequiredValue(skillRequired.required);\n"
				+ "              if(!skillMap.containsKey(id)){\n" + "               score = score-currentValue;\n"
				+ "              } else {\n" + "                def skillGiven = skillMap.get(id);\n"
				+ "                if(skillGiven !=null && getIntValue"
				+ "(skillGiven.level)>getIntValue(skillRequired.level)){\n"
				+ "                  score = score - (currentValue*(getIntValue(skillGiven.level)-getIntValue(skillRequired.level))/10);\n"
				+ "                }\n" + "              }\n" + "            }"
				+ "           def scoreDouble = score/100.0;\n" + "            if(scoreDouble > 0.5){\n"
				+ "              scoreDouble = (1.061 / (1 + Math.pow(Math.E, -7 * (scoreDouble - 0.5)))) - 0.03;\n"
				+ "            }\n" + "            return (int)(scoreDouble*100);";

		Script script = new Script(ScriptType.INLINE, Script.DEFAULT_SCRIPT_LANG, scriptString, params);
		queryBuilder = QueryBuilders.scriptScoreQuery(boolQueryBuilder, script)
				.setMinScore(jobSearchRequest.getMinMatchPercentage());
		return queryBuilder;
	}

}
