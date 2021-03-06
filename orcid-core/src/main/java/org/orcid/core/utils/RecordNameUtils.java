package org.orcid.core.utils;

import org.apache.commons.lang.StringUtils;
import org.orcid.jaxb.model.common_v2.Visibility;
import org.orcid.persistence.jpa.entities.RecordNameEntity;

public class RecordNameUtils {

    public static String getPublicName(RecordNameEntity recordName) {
        if (Visibility.PUBLIC.name().equals(recordName.getVisibility())) {
            if (!StringUtils.isBlank(recordName.getCreditName())) {
                return recordName.getCreditName();
            } else {
                return recordName.getGivenNames() + (StringUtils.isBlank(recordName.getFamilyName()) ? "" : " " + recordName.getFamilyName());
            }
        }
        return null;
    }
    
    public static String getDisplayName(RecordNameEntity recordNameEntity) {
        if(recordNameEntity == null) {
            return null;
        }
        if (StringUtils.isNotBlank(recordNameEntity.getCreditName()) && Visibility.PUBLIC.name().equals(recordNameEntity.getVisibility())) {
            return recordNameEntity.getCreditName();
        }
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(recordNameEntity.getGivenNames())) {
            builder.append(recordNameEntity.getGivenNames());
        }
        if (StringUtils.isNotBlank(recordNameEntity.getFamilyName())) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(recordNameEntity.getFamilyName());
        }
        return builder.length() > 0 ? builder.toString() : null;
    }

}
