package es.us.isa.cristal.neo4j.queries;

import es.us.isa.cristal.model.expressions.AndExpr;
import es.us.isa.cristal.model.expressions.RALExpr;

/**
 * User: resinas
 * Date: 03/03/13
 * Time: 11:14
 */
public class AndExprBuilder implements ExprBuilder {

    private Neo4jQueryBuilder builder;

    public AndExprBuilder(Neo4jQueryBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Class<? extends RALExpr> getExprType() {
        return AndExpr.class;
    }

    @Override
    public Query build(RALExpr expr, Object processId) {
        AndExpr and = (AndExpr) expr;
        Query left = builder.buildQuery(and.getObjectExprLeft(), processId);
        Query right = builder.buildQuery(and.getObjectExprRight(), processId);

        return Query.start(left.getStart(), right.getStart())
                .match(left.getMatch(), right.getMatch())
                .with(left.getWith(), right.getWith())
                .where("(" + left.getWhere() + ") AND (" + right.getWhere() + ")")
                .build();

    }
}
