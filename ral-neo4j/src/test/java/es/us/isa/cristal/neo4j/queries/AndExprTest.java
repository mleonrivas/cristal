package es.us.isa.cristal.neo4j.queries;

import es.us.isa.cristal.model.expressions.RALExpr;
import es.us.isa.cristal.parser.RALParser;
import org.junit.Assert;
import org.junit.Test;
import org.neo4j.cypher.ExecutionResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * User: resinas
 * Date: 03/03/13
 * Time: 11:52
 */
public class AndExprTest extends AbstractBaseTest {

    @Test
    public void shouldQueryAnds() {
        RALExpr expr = RALParser.parse("HAS ROLE Account Administrator AND HAS ROLE Project Coordinator");
        ExecutionResult result = doQuery(expr);

        Set<String> l = getSetFromResult(result);

        Assert.assertEquals(1, l.size());
        Assert.assertEquals(new HashSet<String>(Arrays.asList("Anthony")), l);

    }
}
