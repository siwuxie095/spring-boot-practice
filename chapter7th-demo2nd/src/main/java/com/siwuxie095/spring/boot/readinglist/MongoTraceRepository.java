package com.siwuxie095.spring.boot.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.Trace;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jiajing Li
 * @date 2021-04-22 23:18:20
 */
@SuppressWarnings("all")
@Service
public class MongoTraceRepository implements TraceRepository {

    private MongoOperations mongoOps;

    @Autowired
    public MongoTraceRepository(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    @Override
    public void add(Map<String, Object> traceInfo) {
        mongoOps.save(new Trace(new Date(), traceInfo));
    }

    @Override
    public List<Trace> findAll() {
        return mongoOps.findAll(Trace.class);
    }

}

