package org.sergei.sakila.jdbc;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.sergei.sakila.model.CustomerAddress;
import org.sergei.sakila.model.FormMetaData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Caching class to increase performance but database response into
 * the cache and get it when it needs ot
 *
 * @author Sergei Visotsky
 */
public class CachingDataAccessObject implements IDataAccessObject {

    private Cache<ParametersKey, Object> cache;

    private final IDataAccessObject dao;

    /**
     * Define cache size, expiration after write and more things
     *
     * @param dao define DAO object to get persistence methods
     */
    public CachingDataAccessObject(IDataAccessObject dao) {
        this.dao = dao;
        cache = CacheBuilder.newBuilder()
                .maximumSize(800)
                .expireAfterWrite(8, TimeUnit.HOURS)
                .build();
    }

    @Override
    public FormMetaData getFormMetaData(long formId, String viweName, String langType) {
        FormMetaData formMetaData = null;
        try {
            // puts and gets data from the cache by roles defined in constructor
            formMetaData = (FormMetaData) cache.get(new ParametersKey(formId, langType),
                    () -> dao.getFormMetaData(formId, viweName, langType));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return formMetaData;
    }

    @Override
    public List<CustomerAddress> getAddressesOfAllCustomers() {
        return dao.getAddressesOfAllCustomers();
    }

    private static class ParametersKey {
        Long formId;
        String langType;

        ParametersKey(Long formId, String langType) {
            this.formId = formId;
            this.langType = langType;
        }

        public Long getFormId() {
            return formId;
        }

        public void setFormId(Long formId) {
            this.formId = formId;
        }

        public String getLangType() {
            return langType;
        }

        public void setLangType(String langType) {
            this.langType = langType;
        }
    }
}
