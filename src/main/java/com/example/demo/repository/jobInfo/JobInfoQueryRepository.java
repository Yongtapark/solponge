package com.example.demo.repository.jobInfo;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.domain.jobInfo.QJobInfo;
import com.example.demo.domain.member.Member;
import com.example.demo.utils.SearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.domain.jobInfo.QJobInfo.jobInfo;
import static com.example.demo.domain.member.QMember.member;


public class JobInfoQueryRepository {
    private final JPAQueryFactory query;
    private final JobInfoRepository jobInfoRepository;

    public JobInfoQueryRepository(EntityManager em, JobInfoRepository jobInfoRepository) {
        this.query = new JPAQueryFactory(em);
        this.jobInfoRepository = jobInfoRepository;
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
