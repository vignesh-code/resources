package com.coeus.helpers.graphql;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GraphQLHelper {

    public static List<String> getNestedFields(DataFetchingEnvironment environment, String parent) {

        List<String> nestedFields = new ArrayList<>();

        List<SelectedField> fields = environment.getSelectionSet().getFields(parent);

        for (SelectedField item: fields) {
            List<String> names = item.getSelectionSet()
                    .getFields()
                    .stream()
                    .map(SelectedField::getName)
                    .collect(Collectors.toList());

            nestedFields.addAll(names);
       }

        return nestedFields;
    }

}
