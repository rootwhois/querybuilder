package com.example.querybuilder_backend.util;

import cn.hutool.core.util.StrUtil;
import com.example.querybuilder_backend.eneity.Children;
import com.example.querybuilder_backend.eneity.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryBuilderUtil {

    @Autowired
    private QueryBuilderProperties queryBuilderProperties;

    public String getSql(Query query) {
        return getSql(query, "") + ";";
    }

    public String getSql(Query query, String sql) {
        List<String> list = new ArrayList<>();
        for (Children child : query.getChildren()) {
            if (child.getType().equals("query-builder-rule")) {
                Query childQuery = child.getQuery();
                list.add(childQuery.getRule());
                list.add(queryBuilderProperties.getMapping().getOrDefault(childQuery.getOperator(), "="));

                if (StrUtil.isNotEmpty(childQuery.getValue())) {
                    String field = "'";

                    if (childQuery.getOperator().equals("ends with")) {
                        field += "%";
                    }
                    field += childQuery.getValue();
                    if (childQuery.getOperator().equals("begins with")) {
                        field += "%";
                    }
                    field += "'";
                    list.add(field);
                }
            } else {
                list.add(" ( ");
                list.add(getSql(child.getQuery(), sql));
                list.add(" ) ");
            }
            list.add(queryBuilderProperties.getMapping().get(query.getLogicalOperator()));
        }
        list.remove(list.size() - 1);
        return sql += listToStr(list);
    }

    public <T> String listToStr(List<T> deque) {
        StringBuilder sb = new StringBuilder();
        for (T t : deque) {
            sb.append(t)
                    .append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
