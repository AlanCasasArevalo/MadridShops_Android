package com.alancasasarevalo.madridshops.repository.db.dao

import ActivityDBConstants
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.alancasasarevalo.madridshops.repository.db.DBHelper
import com.alancasasarevalo.madridshops.repository.model.ActivityEntity


internal class ActivityDAO(dbHelper: DBHelper) : DAOPersistable<ActivityEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun query(id: Long): ActivityEntity {
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return entityFromCursor(cursor)!!

    }

    override fun query(): List<ActivityEntity> {

        val queryResult = arrayListOf<ActivityEntity>()

        val cursor = dbReadOnlyConnection.query(ActivityDBConstants.TABLE_ACTIVITY,
                ActivityDBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                ActivityDBConstants.KEY_ACTIVITY_DATABASE_ID)

        while (cursor.moveToNext()) {
            val activityEntity = entityFromCursor(cursor)
            queryResult.add(activityEntity!!)
        }

        return queryResult
    }

    fun entityFromCursor(cursor: Cursor): ActivityEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }

        return ActivityEntity(
                cursor.getLong(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_NAME)),
                cursor.getString(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_OPENING_HOURS)),
                cursor.getString(cursor.getColumnIndex(ActivityDBConstants.KEY_ACTIVITY_ADDRESS))
        )

    }


    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(ActivityDBConstants.TABLE_ACTIVITY,
                ActivityDBConstants.ALL_COLUMNS,
                ActivityDBConstants.KEY_ACTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                ActivityDBConstants.KEY_ACTIVITY_DATABASE_ID
        )

        return cursor
    }

    override fun insert(element: ActivityEntity): Long {
        var id: Long = 0

        id = dbReadWriteConnection.insert(ActivityDBConstants.TABLE_ACTIVITY, null, contentValues(element))

        return id
    }

    override fun update(id: Long, element: ActivityEntity): Long {

        val numberOfRecordsUpdate = dbReadWriteConnection.update(
                ActivityDBConstants.TABLE_ACTIVITY,
                contentValues(element),
                ActivityDBConstants.KEY_ACTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString())
        ).toLong()

        return numberOfRecordsUpdate
    }

    override fun delete(element: ActivityEntity): Long {
        if (element.dataBaseId < 1) {
            return 0
        }
        return delete(element.dataBaseId)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(ActivityDBConstants.TABLE_ACTIVITY,
                ActivityDBConstants.KEY_ACTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString())).toLong()
    }

    override fun deleteAll(): Boolean {

        return dbReadWriteConnection.delete(ActivityDBConstants.TABLE_ACTIVITY,
                null,
                null).toLong() >= 0
    }

    fun contentValues(activityEntity: ActivityEntity): ContentValues {
        val content = ContentValues()

        content.put(ActivityDBConstants.KEY_ACTIVITY_ID_JSON, activityEntity.id)
        content.put(ActivityDBConstants.KEY_ACTIVITY_NAME, activityEntity.name)
        content.put(ActivityDBConstants.KEY_ACTIVITY_DESCRIPTION, activityEntity.description)
        content.put(ActivityDBConstants.KEY_ACTIVITY_LATITUDE, activityEntity.latitude)
        content.put(ActivityDBConstants.KEY_ACTIVITY_LONGITUDE, activityEntity.longitude)
        content.put(ActivityDBConstants.KEY_ACTIVITY_IMAGE_URL, activityEntity.img)
        content.put(ActivityDBConstants.KEY_ACTIVITY_LOGO_IMAGE_URL, activityEntity.logo)
        content.put(ActivityDBConstants.KEY_ACTIVITY_ADDRESS, activityEntity.address)
        content.put(ActivityDBConstants.KEY_ACTIVITY_OPENING_HOURS, activityEntity.opening_hours_es)

        return content
    }

}
