package by.nnhorushko.archivedhibernateentity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@FilterDef(name = "archivedFilter", parameters = @ParamDef(name = "isArchived", type = "boolean"))
@Filters({
        @Filter(name = "archivedFilter", condition = "archived = :isArchived")
})
public abstract class ArchivableEntity {
    public static String ARCHIVE_FILTER = "archivedFilter";
    public static String ARCHIVE_PARAMETER = "isArchived";
}
