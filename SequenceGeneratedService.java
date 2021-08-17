package com.lara.model;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratedService {
	
	@Autowired
	private MongoOperations mongoOperation;
	
	public int getSequenceNumber(String sequenceName) {
		//get sequence number
		Query query = new Query(Criteria.where("id").is(sequenceName));
		//update sequence number
		Update update = new Update().inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
	    options.returnNew(true).upsert(true);
		//modify in documents
		DbSequence counter = mongoOperation.findAndModify(query, update, options,//.returnNew(true)
				//.upsert(true),DbSequence.class);
				DbSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
