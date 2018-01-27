package com.alancasasarevalo.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.alancasasarevalo.repository.db.DBConstants
import com.alancasasarevalo.repository.db.DBHelper
import com.alancasasarevalo.repository.model.ShopEntity

class ShopDAO ( dbHelper: DBHelper ) : DAOPersistable<ShopEntity> {

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun query(id: Long): ShopEntity{
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return ShopEntity(1,
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_SHOP_LATITUDE)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_ADDRESS))
        )

    }

    override fun query(): List<ShopEntity> {
        return arrayListOf()
    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOP_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_SHOP_ID
        )

        return cursor
    }

    override fun insert(element: ShopEntity): Long {
        var id: Long = 0

        id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOP, null, contentValues(element))

        return id
    }

    override fun update(id: Long, element: ShopEntity): Long {
        // TODO: REpara est
        return 1
    }

    override fun delete(element: ShopEntity): Long {
        return delete(element.id)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(DBConstants.TABLE_SHOP,
                DBConstants.KEY_SHOP_ID + " = ?",
                arrayOf(id.toString())).toLong()
    }

    override fun deleteAll(): Boolean {
        // TODO: Haz esto mamon
        return true
    }

    fun contentValues(shopEntity: ShopEntity) : ContentValues{
        val content = ContentValues()

        content.put(DBConstants.KEY_SHOP_ID, shopEntity.id)
        content.put(DBConstants.KEY_SHOP_NAME, shopEntity.name)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION, shopEntity.description)
        content.put(DBConstants.KEY_SHOP_LATITUDE, shopEntity.latitude)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, shopEntity.longitude)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopEntity.image)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS, shopEntity.openingHours)

        return content
    }

}











