package com.example;

import java.util.HashMap;
import java.util.Map;

public class CachedDataSource implements IDataSource {
    private final IDataSource dataSource;
    private final Map<RecordLocator, String> cache = new HashMap<>();

    public CachedDataSource(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getRecord(RecordLocator recordLocator) {
        if (!cache.containsKey(recordLocator)) {
            cache.put(recordLocator, dataSource.getRecord(recordLocator));
        }
        return cache.get(recordLocator);
    }
}
