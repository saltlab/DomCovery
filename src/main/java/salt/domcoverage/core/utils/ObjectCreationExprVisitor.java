package salt.domcoverage.core.utils;

import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;

public class ObjectCreationExprVisitor extends VoidVisitorAdapter {

	private ArrayList<String> elementsToCover = new ArrayList<String>();

	ArrayList<Expression> objectCreationExprs = new ArrayList<Expression>();

	public ArrayList<Expression> getObjectCreationExprs() {
		return objectCreationExprs;
	}

	public void visit(ObjectCreationExpr oce, Object arg) {

		addToObjectCreationExprs(oce);

	}

	private void addToObjectCreationExprs(ObjectCreationExpr oce) {
		for (String elementThatAccessDOM : elementsToCover) {
			if (oce.getType().getName().contains(elementThatAccessDOM)) {
				objectCreationExprs.add(oce);
			}
		}

	}

	public void applyFilter(ArrayList<String> etc) {

		elementsToCover = etc;
	}

}
