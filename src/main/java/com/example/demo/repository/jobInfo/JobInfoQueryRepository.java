package com.example.demo.repository.jobInfo;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.repository.companyScrap.CompanyScrapRepository;
import com.example.demo.repository.infoScrap.InfoScrapRepository;
import com.example.demo.utils.SearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.domain.companyScrap.QCompanyScrap.companyScrap;
import static com.example.demo.domain.infoScrap.QInfoScrap.infoScrap;
import static com.example.demo.domain.jobInfo.QJobInfo.jobInfo;


public class JobInfoQueryRepository {
    private final JPAQueryFactory query;
    private final JobInfoRepository jobInfoRepository;
    private final InfoScrapRepository infoScrapRepository;

    private final CompanyScrapRepository companyScrapRepository;

    public JobInfoQueryRepository(EntityManager em, JobInfoRepository jobInfoRepository, InfoScrapRepository infoScrapRepository, CompanyScrapRepository companyScrapRepository) {
        this.query = new JPAQueryFactory(em);
        this.jobInfoRepository = jobInfoRepository;
        this.infoScrapRepository = infoScrapRepository;
        this.companyScrapRepository = companyScrapRepository;
    }

    private List<JobInfo> paginateJobInfos(List<JobInfo> jobInfos, Pageable pageable){
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), jobInfos.size());
        return jobInfos.subList(start,end);
    }
    private List<JobInfo> searchJobInfos(SearchCond cond){
        return query.selectFrom(jobInfo)
                .where(searchBySelect((cond.getSearchSelect()), cond.getSearchValue()))
                .fetch();
    }


    public Page<JobInfo> search(SearchCond cond, Pageable pageable) {
        List<JobInfo> searchJobInfos = searchJobInfos(cond);
        List<JobInfo> paginateJobInfos = paginateJobInfos(searchJobInfos, pageable);
        return new PageImpl<>(paginateJobInfos, pageable, paginateJobInfos.size());
    }


    private BooleanExpression searchBySelect(String searchSelect, String searchValue){
        if(StringUtils.hasText(searchSelect)&& StringUtils.hasText(searchValue)){
            switch (searchSelect){
                case "all":
                    return jobInfo.jobInfoCompanyName.contains(searchValue)
                            .or(jobInfo.jobInfoPostingName.contains(searchValue))
                            .or(jobInfo.jobInfoFieldTagList.contains(searchValue))
                            .or(jobInfo.jobInfoQualification.contains(searchValue))
                            .or(jobInfo.jobInfoWorkType.contains(searchValue));
                case "jobInfoCompanyName":
                    return jobInfo.jobInfoCompanyName.contains(searchValue);
                case "jobInfoPostingName" :
                    return jobInfo.jobInfoPostingName.contains(searchValue);
                case "jobInfoFieldTagList" :
                    return jobInfo.jobInfoFieldTagList.contains(searchValue);
                case "jobInfoQualification" :
                    return jobInfo.jobInfoQualification.contains(searchValue);
                case "jobInfoWorkType" :
                    return jobInfo.jobInfoWorkType.contains(searchValue);
            }
        }
        return null;
    }

    /**
     * infoScrap
     */

    /*마이페이지 scrapInfo*/
    public List<JobInfo> myScrapJobInfo(Long memberNum){
        List<String> scrap = query.select(infoScrap.infoName)
                .from(infoScrap)
                .where(infoScrap.memberNum.eq(memberNum))
                .fetch();

        List<JobInfo> scrappedJobInfo = query.selectFrom(jobInfo)
                .where(jobInfo.jobInfoPostingName.in(scrap))
                .fetch();
        return scrappedJobInfo;
    }
    /*마이페이지 scrapInfo 페이징*/
    public Page<JobInfo> myScrapjobInfoPage(Long memberNum, Pageable pageable){
        List<JobInfo> jobInfos = myScrapJobInfo(memberNum);
        List<JobInfo> paginatedJobInfos = paginateJobInfos(jobInfos, pageable);
        return new PageImpl<>(paginatedJobInfos, pageable, jobInfos.size());

    }

    /**
     * companyScrap
     */
    /*마이페이지 companyScrap*/
    public List<JobInfo> myScrapCompany(Long memberNum){
        List<String> scrap = query.select(companyScrap.companyName)
                .from(companyScrap)
                .where(companyScrap.memberNum.eq(memberNum))
                .fetch();

        List<JobInfo> scrappedCompany = query.selectFrom(jobInfo)
                .where(jobInfo.jobInfoCompanyName.in(scrap))
                .fetch();
        return scrappedCompany;
    }


    /*마이페이지 companyScrap 페이징*/
    public Page<JobInfo> myScrapCompanyPage(Long memberNum, Pageable pageable){
        List<JobInfo> jobInfos = myScrapCompany(memberNum);
        List<JobInfo> paginatedJobInfos = paginateJobInfos(jobInfos, pageable);
        return new PageImpl<>(paginatedJobInfos, pageable, jobInfos.size());

    }
    /*companyScrap 의 공고수*/
    public Map<String, Long> MyScrapCompanyAnnouncement(Long memberNum) {
        List<JobInfo> scrappedJobInfos = myScrapCompany(memberNum);

        /*Map<String, Long> jobInfoCounts = new HashMap<>();

        for (JobInfo jobInfo : scrappedJobInfos) {
            String companyName = jobInfo.getJobInfoCompanyName();
            if (jobInfoCounts.containsKey(companyName)) {
                jobInfoCounts.put(companyName, jobInfoCounts.get(companyName) + 1);
            } else {
                jobInfoCounts.put(companyName, 1L);
            }
        }*/


        // 스크랩한 회사의 공고 목록을 스트림으로 처리하여 회사별 공고 개수를 계산하고, Map에 저장합니다.
        Map<String, Long> jobInfoCounts = scrappedJobInfos.stream()
                .collect(Collectors.groupingBy(JobInfo::getJobInfoCompanyName, Collectors.counting()));


        return jobInfoCounts;
    }



    public List<JobInfo> getJopInfoNewTop8List(){
        return query.selectFrom(jobInfo)
                .distinct()
                .orderBy(jobInfo.jobInfoNum.desc())
                .limit(8)
                .fetch();

    }
    public List<JobInfo> getCompanyToJobInfoList(){
        return null;
    }
    public List<JobInfo> getCompanyInScrapList(){
        return null;
    }
    public List<JobInfo> getInfoInScrapList(){
        return null;
    }


}
