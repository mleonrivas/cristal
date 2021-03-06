package es.us.isa.cristal.neo4j.queries;

import es.us.isa.cristal.model.expressions.NegativeExpr;
import es.us.isa.cristal.model.expressions.RALExpr;

/**
 * User: resinas
 * Date: 27/02/13
 * Time: 10:26
 */
public class NegativeExprBuilder implements ExprBuilder {

    private Neo4jQueryBuilder builder;

    public NegativeExprBuilder(Neo4jQueryBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Class<? extends RALExpr> getExprType() {
        return NegativeExpr.class;
    }

    @Override
    public Query build(RALExpr expr, Object processId) {
        NegativeExpr negativeExpr = (NegativeExpr) expr;
        Query q = builder.buildQuery(negativeExpr.getExprObject(), processId);

        return Query.start(q.getStart())
                .match(q.getMatch())
                .with(q.getWith())
                .where("NOT (" + q.getWhere() + ")").build();
    }
}
