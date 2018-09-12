package com.kaipin.search.core.query;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;

import org.wltea.analyzer.lucene.IKAnalyzer;

import com.kaipin.search.core.dimension.Company;
import com.kaipin.search.core.dimension.Live;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.core.dimension.SchInfo;
import com.kaipin.search.core.dimension.StuUser;
import com.kaipin.search.core.index.SearchHelper;

public class WebBuildQueryFactory implements QueryFactory {
    private final static BooleanQuery nullQuery = new BooleanQuery();

    private final static IKAnalyzer analyzer = new IKAnalyzer();

    private final static Log log = LogFactory.getLog(SearchHelper.class);

    @Override
    public Query createPositionQuery(Map<String, Object> map, float boost) {

        return buildWebQuery(Position.TITLE, Position.LOCATION, Position.INDUSTRY, map, boost);
    }

    @Override
    public Query createCompanyQuery(Map<String, Object> map, float boost) {

        return buildWebQuery(Company.TITLE, Company.LOCATION, Company.INDUSTRY, map, boost);
    }

    @Override
    public Query createLiveQuery(Map<String, Object> map, float boost) {

        return buildWebQuery(Live.TITLE, Live.LOCATION, Live.INDUSTRY, map, boost);
    }

    @Override
    public Query createStuQuery(Map<String, Object> map, float boost) {
        return buildWebQuery(StuUser.TITLE, StuUser.LOCATION, StuUser.INDUSTRY, map, boost);
    }

    @Override
    public Query createSchQuery(Map<String, Object> map, float boost) {
        return buildWebQuery(SchInfo.TITLE, SchInfo.LOCATION, SchInfo.INDUSTRY, map, boost);
    }

    private Query buildWebQuery(String key_title, String key_location, String key_industry,
            Map<String, Object> map, float boost) {

        if (MapUtils.isEmpty(map))
            return nullQuery;

        try {

            BooleanQuery bq = new BooleanQuery();

            Query q = null;

            // 关键字
            QueryParser parser = new QueryParser(Version.LUCENE_40, key_title, analyzer);

            parser.setDefaultOperator(QueryParser.OR_OPERATOR);

            String keyword = MapUtils.getString(map, key_title);

            // q = parser.parse(keyword );//+ " " + keyword +""

            // 模糊查询
            q = new WildcardQuery(
                    new Term(key_title, "*" + MapUtils.getString(map, key_title) + "*"));


            bq.add(q, BooleanClause.Occur.MUST);

            // 地区
            List<String> locationCode = (List<String>) MapUtils.getObject(map, key_location);

            if (locationCode != null && locationCode.size() > 0) {
                int size = locationCode.size();

                String[] queries = new String[size];

                String[] fields = new String[size];

                BooleanClause.Occur[] clauses = new BooleanClause.Occur[size];
                String lcode;
                for (int pos = 0; pos < size; pos++) {
                    lcode = locationCode.get(pos);

                    queries[pos] = lcode;

                    fields[pos] = key_location;

                    clauses[pos] = BooleanClause.Occur.SHOULD;
                }

                q = MultiFieldQueryParser.parse(Version.LUCENE_40, queries, fields, clauses,
                        analyzer);

                bq.add(q, BooleanClause.Occur.MUST);
            }

            // 行业
            String industryCode = MapUtils.getString(map, key_industry);

            if (!StringUtils.isBlank(industryCode)) {

                q = new TermQuery(new Term(key_industry, industryCode));

                bq.add(q, BooleanClause.Occur.MUST);
            }

            bq.setBoost(boost);

            // 模糊查询
            // Query query=new FuzzyQuery(new Term(key_title,MapUtils.getString(map,
            // key_title)+"*"));


            return bq;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

}
