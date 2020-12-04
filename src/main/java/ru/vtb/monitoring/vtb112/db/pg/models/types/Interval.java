package ru.vtb.monitoring.vtb112.db.pg.models.types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGInterval;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

// vladmihalcea/hibernate-types
public class Interval implements UserType {

    private static final int[] SQL_TYPES = {Types.OTHER};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        final PGInterval interval = (PGInterval) rs.getObject(names[0]);

        if (rs.wasNull() || interval == null) {
            return null;
        }

        final int days = interval.getDays();
        final int hours = interval.getHours();
        final int minutes = interval.getMinutes();
        final double seconds = interval.getSeconds();

        return Duration.ofDays(days)
                .plus(hours, ChronoUnit.HOURS)
                .plus(minutes, ChronoUnit.MINUTES)
                .plus((long) Math.floor(seconds), ChronoUnit.SECONDS);
    }

    @Override
    public Class<?> returnedClass() {
        return Duration.class;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        if (!(value instanceof Duration)) {
            st.setNull(index, Types.OTHER);
        } else {
            Duration duration = (Duration) value;
            final int days = (int) duration.toDays();
            final int hours = (int) (duration.toHours() % 24);
            final int minutes = (int) (duration.toMinutes() % 60);
            final double seconds = duration.getSeconds() % 60;
            st.setObject(index, new PGInterval(0, 0, days, hours, minutes, seconds));
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

}
