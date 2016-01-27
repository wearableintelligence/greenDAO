package de.greenrobot.daotest2.specialdao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.database.Database;
import de.greenrobot.dao.database.DatabaseStatement;

import de.greenrobot.daotest2.dao.DaoSession;

import de.greenrobot.daotest2.to1_specialentity.ToOneTarget2;

import de.greenrobot.daotest2.specialentity.RelationSource2;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RELATION_SOURCE2".
*/
public class RelationSource2Dao extends AbstractDao<RelationSource2, Long> {

    public static final String TABLENAME = "RELATION_SOURCE2";

    /**
     * Properties of entity RelationSource2.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ToOneId = new Property(1, Long.class, "toOneId", false, "TO_ONE_ID");
    };

    private DaoSession daoSession;


    public RelationSource2Dao(DaoConfig config) {
        super(config);
    }
    
    public RelationSource2Dao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RELATION_SOURCE2\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TO_ONE_ID\" INTEGER);"); // 1: toOneId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RELATION_SOURCE2\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(DatabaseStatement stmt, RelationSource2 entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long toOneId = entity.getToOneId();
        if (toOneId != null) {
            stmt.bindLong(2, toOneId);
        }
    }

    @Override
    protected void attachEntity(RelationSource2 entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RelationSource2 readEntity(Cursor cursor, int offset) {
        RelationSource2 entity = new RelationSource2( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1) // toOneId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RelationSource2 entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setToOneId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(RelationSource2 entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(RelationSource2 entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getToOneTarget2Dao().getAllColumns());
            builder.append(" FROM RELATION_SOURCE2 T");
            builder.append(" LEFT JOIN TO_ONE_TARGET2 T0 ON T.\"TO_ONE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected RelationSource2 loadCurrentDeep(Cursor cursor, boolean lock) {
        RelationSource2 entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ToOneTarget2 toOneTarget2 = loadCurrentOther(daoSession.getToOneTarget2Dao(), cursor, offset);
        entity.setToOneTarget2(toOneTarget2);

        return entity;    
    }

    public RelationSource2 loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<RelationSource2> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<RelationSource2> list = new ArrayList<RelationSource2>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<RelationSource2> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<RelationSource2> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
