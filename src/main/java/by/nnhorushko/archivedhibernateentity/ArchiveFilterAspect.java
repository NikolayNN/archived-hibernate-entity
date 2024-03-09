package by.nnhorushko.archivedhibernateentity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Aspect
@Component
public class ArchiveFilterAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Around("@within(archivableCrudService) && execution(public * *(..))")
    public Object applyArchiveFilterBasedOnAnnotation(ProceedingJoinPoint joinPoint, ArchivableCrudService archivableCrudService) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ArchiveFilter archiveFilter = signature.getMethod().getAnnotation(ArchiveFilter.class);
        ArchiveFilter.State state = archiveFilter != null ? archiveFilter.value() : archivableCrudService.value();

        if (state == ArchiveFilter.State.ALL) {
            return joinPoint.proceed(); // Не применяем никакие фильтры
        }

        boolean isArchived = (state == ArchiveFilter.State.ARCHIVED);
        return applyFilter(joinPoint, isArchived);
    }

    private Object applyFilter(ProceedingJoinPoint joinPoint, boolean isArchived) throws Throwable {
        Session session = entityManager.unwrap(Session.class);
        try {
            session.enableFilter(ArchivableEntity.ARCHIVE_FILTER).setParameter(ArchivableEntity.ARCHIVE_PARAMETER, isArchived);
            return joinPoint.proceed();
        } finally {
            session.disableFilter(ArchivableEntity.ARCHIVE_FILTER);
        }
    }
}
