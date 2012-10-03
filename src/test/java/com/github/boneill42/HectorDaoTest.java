package com.github.boneill42;

import java.util.Date;

import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.hector.api.beans.Composite;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HectorDaoTest {
    private static Logger LOG = LoggerFactory.getLogger(HectorDaoTest.class);

    @Test
    public void testDao() throws Exception {
       HectorDao dao = new HectorDao("localhost:9160", "example");
       dumpFishBlog(dao.readFishBlog("fishblogs", "boneill42"));
    }
        
    public void dumpFishBlog(ColumnFamilyResult<String, Composite> columns){
        for (Composite columnName : columns.getColumnNames()){
            FishBlog fishBlog = new FishBlog(columnName);
            LOG.debug("fishBlog.when=>[" + new Date(fishBlog.getWhen()) + "]");
            LOG.debug("fishBlog.type=>[" + fishBlog.getType() + "]");
            LOG.debug("fishBlog.field=>[" + fishBlog.getField() + "]");
            LOG.debug("fishBlog.value=>[" + columns.getString(columnName) + "]");
        }        
    }
}
