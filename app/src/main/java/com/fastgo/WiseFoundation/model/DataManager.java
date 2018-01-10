package com.fastgo.WiseFoundation.model;

import com.fastgo.WiseFoundation.model.db.IDBProvider;
import com.fastgo.WiseFoundation.model.http.IHttpProvider;
import com.fastgo.WiseFoundation.model.prefs.ISPProvider;

/**
 *
 */

public class DataManager implements IHttpProvider, IDBProvider, ISPProvider {
    IHttpProvider mRetrofitProvider;
    IDBProvider mDbProvider;
    ISPProvider mSpProvider;

    public DataManager(IHttpProvider retrofitProvider, IDBProvider dbProvider, ISPProvider spProvider){
        mRetrofitProvider = retrofitProvider;
        mDbProvider = dbProvider;
        mSpProvider = spProvider;
    }

}
