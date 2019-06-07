package cn.edu.bnuz.bell.orm

import grails.util.Holders
import groovy.util.logging.Log4j
import org.hibernate.boot.model.relational.Namespace
import org.hibernate.boot.model.relational.Sequence
import org.hibernate.mapping.Table
import org.hibernate.tool.schema.spi.SchemaFilter
import org.hibernate.tool.schema.spi.SchemaFilterProvider

/**
 * 数据库Schema过滤器Provider
 * @author Yang Lin
 */
@Log4j
class BellSchemaFilterProvider implements SchemaFilterProvider {
    private BellSchemaFilter filter = new BellSchemaFilter()
    SchemaFilter getCreateFilter() {
        return filter
    }

    SchemaFilter getDropFilter() {
        return filter
    }

    SchemaFilter getMigrateFilter() {
        return filter
    }

    SchemaFilter getValidateFilter() {
        return filter
    }

    static class BellSchemaFilter implements SchemaFilter {
        def ignoredSchemas = Holders.config.bell.orm.ignored.schemas
        def ignoredPrefixes = Holders.config.bell.orm.ignored.prefixes

        boolean includeNamespace(Namespace namespace) {
            if (ignoredSchemas && ignoredSchemas.contains(namespace.name.schema.toString())) {
                log.debug("Ignored scheme: ${namespace.name.schema.toString()}")
                return false
            }
            return true
        }

        boolean includeTable(Table table) {
            if (ignoredSchemas && ignoredSchemas.contains(table.schema.toString())) {
                log.debug("Ignored scheme: $table.name")
                return false
            }

            if (ignoredPrefixes && ignoredPrefixes.any{table.name.startsWith("${it}_")}) {
                log.debug "Ignored table: $table.name"
                return false
            }

            return true
        }

        boolean includeSequence(Sequence sequence) {
            return true
        }
    }
}
