package ua.org.shutl.buoyviewer.dao;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.List;

/**
 * Created by shutl on 07.01.16.
 */
public abstract class GenericDao<T extends Model> {

    private Class<T> clazz;

    public GenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> getAll(){
        return SQLite.select().from(clazz).queryList();
    }

    public void save(T t){
        t.save();
    }

    public void update(T t){
        t.update();
    }

    public void delete(T t){
        t.delete();
    }

    public void clearTable(){
        SQLite.delete().from(clazz).execute();
    }

}
