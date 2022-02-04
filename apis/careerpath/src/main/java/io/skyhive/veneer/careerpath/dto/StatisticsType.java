package io.skyhive.veneer.careerpath.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
public enum StatisticsType {
    // <summary>Undefined, shell not be used</summary>
    // <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.None">`StatisticsType.None` on google.com</a></footer>
    None,
    /// <summary>
    /// Future skills for job
    /// Calculated based on marked data using prediction model for WW only
    /// https://theskyhive.visualstudio.com/AI/_workitems/edit/1231/
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.JobFutureSkills">`StatisticsType.JobFutureSkills` on google.com</a></footer>
    JobFutureSkills,
    /// <summary>
    /// Job Subject Matter, or Thought Leadership Skills
    /// Calculated based on articles collected from internet (written by Subject matter experts/thought leaders)
    /// https://theskyhive.visualstudio.com/AI/_workitems/edit/1231/
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.JobThoughtLeadershipSkills">`StatisticsType.JobThoughtLeadershipSkills` on google.com</a></footer>
    JobThoughtLeadershipSkills,
    /// <summary>
    /// Popular Skills among jobs based on market data, as normalized vector
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.JobMarketStatistic">`StatisticsType.JobMarketStatistic` on google.com</a></footer>
    JobMarketStatistic,
    /// <summary>
    /// Popular Skills among resumes based on market data, as normalized vector
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.ResumeStatistic">`StatisticsType.ResumeStatistic` on google.com</a></footer>
    ResumeStatistic,
    /// <summary>
    /// Popular skills among jobs substracting popular skills among resumes
    /// e.g. default skills which typically used on job but not mentioned in resumes (could be better verssion of fuzzy matching)
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.DefaultResumeSkillsStatistics">`StatisticsType.DefaultResumeSkillsStatistics` on google.com</a></footer>
    DefaultResumeSkillsStatistics,
    /// <summary>
    /// Intersection between job market skills and resume skills
    /// Should be most important for matching
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.CoreSkills">`StatisticsType.CoreSkills` on google.com</a></footer>
    CoreSkills,
    /// <summary>
    /// Opposite to Future skills - those which are on decline, fullfilled by ML for WW only
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.JobDisappearingSkills">`StatisticsType.JobDisappearingSkills` on google.com</a></footer>
    JobDisappearingSkills,
    /// <summary>
    /// Popular Skills among jobs based on market data, as percentage
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.JobMarketPercentageStatistic">`StatisticsType.JobMarketPercentageStatistic` on google.com</a></footer>
    JobMarketPercentageStatistic,
    /// <summary>
    /// Popular Skills among resumes based on market data, as percentage
    /// </summary>
    /// <footer><a href="https://www.google.com/search?q=SkyHive.Scrapes.ApiModel.StatisticsType.ResumeMarketPercentageStatistic">`StatisticsType.ResumeMarketPercentageStatistic` on google.com</a></footer>
    ResumeMarketPercentageStatistic;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
