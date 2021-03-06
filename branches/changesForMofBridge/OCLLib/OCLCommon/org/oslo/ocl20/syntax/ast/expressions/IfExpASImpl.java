
package org.oslo.ocl20.syntax.ast.expressions;

import uk.ac.kent.cs.kmf.patterns.Visitor;

public class IfExpASImpl

  extends
    
      OclExpressionASImpl
    
  
  implements IfExpAS,
             expressionsVisitable
{
  //--- Constructors ---
  public IfExpASImpl() {

    _id=_nextId++;

  }







    
  //uni1to1
  protected OclExpressionAS thenExpression = null;
  public OclExpressionAS getThenExpression() {
   return thenExpression;
  }
  public void setThenExpression(OclExpressionAS thenExpression) {
    this.thenExpression = thenExpression;
  }


    
  //bi1to1
  protected OclExpressionAS elseExpression = null;
  public OclExpressionAS getElseExpression() {
   return this.elseExpression;
  }
  public void setElseExpression(OclExpressionAS elseExpression) {
    this.elseExpression = elseExpression;
    if (elseExpression.getIfExpAS() != this) {
       elseExpression.setIfExpAS(this);
    }
  }


    
  //uni1to1
  protected OclExpressionAS condition = null;
  public OclExpressionAS getCondition() {
   return condition;
  }
  public void setCondition(OclExpressionAS condition) {
    this.condition = condition;
  }





  //--- Visitable ---
  public java.lang.Object accept(Visitor visitor, java.lang.Object data) {
    if (visitor instanceof expressionsVisitor)
      return ((expressionsVisitor)visitor).visit(this,data);
    else
      return visitor.visit(this,data);
  }

  public void destroy(uk.ac.kent.cs.kmf.patterns.Factory factory) {
  	factory.destroy("IfExpAS",this);
  }

  public void destroy(uk.ac.kent.cs.kmf.patterns.Repository rep) {
  	rep.removeElement("uk.ac.kent.cs.ocl20.syntax.ast.expressions.IfExpAS",this);
  }


  //--- java.lang.Object ---

  static int _nextId=0;
   int _id=0;


  public java.lang.String toString() {
      return "IfExpAS {"+

  _id+
"}";
  }

  public boolean equals(java.lang.Object o) {
    if (o instanceof IfExpAS) {
      try {
        IfExpAS obj = (IfExpAS)o;
        return (this == o);
      } catch (NullPointerException e) {
        return this == o;
      }
    }
    return false;
  }

  public int hashCode() {
      return 0;
  }

  public java.lang.Object clone() {
    IfExpAS obj = new IfExpASImpl();

    return obj;
  }
}



