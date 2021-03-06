
package org.oslo.ocl20.syntax.ast.expressions;

import uk.ac.kent.cs.kmf.patterns.Visitor;

public class CollectionItemASImpl

  extends
    
      CollectionLiteralPartASImpl
    
  
  implements CollectionItemAS,
             expressionsVisitable
{
  //--- Constructors ---
  public CollectionItemASImpl() {

    _id=_nextId++;

  }







    
  //uni1to1
  protected OclExpressionAS item = null;
  public OclExpressionAS getItem() {
   return item;
  }
  public void setItem(OclExpressionAS item) {
    this.item = item;
  }





  //--- Visitable ---
  public java.lang.Object accept(Visitor visitor, java.lang.Object data) {
    if (visitor instanceof expressionsVisitor)
      return ((expressionsVisitor)visitor).visit(this,data);
    else
      return visitor.visit(this,data);
  }

  public void destroy(uk.ac.kent.cs.kmf.patterns.Factory factory) {
  	factory.destroy("CollectionItemAS",this);
  }

  public void destroy(uk.ac.kent.cs.kmf.patterns.Repository rep) {
  	rep.removeElement("uk.ac.kent.cs.ocl20.syntax.ast.expressions.CollectionItemAS",this);
  }


  //--- java.lang.Object ---

  static int _nextId=0;
   int _id=0;


  public java.lang.String toString() {
      return "CollectionItemAS {"+

  _id+
"}";
  }

  public boolean equals(java.lang.Object o) {
    if (o instanceof CollectionItemAS) {
      try {
        CollectionItemAS obj = (CollectionItemAS)o;
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
    CollectionItemAS obj = new CollectionItemASImpl();

    return obj;
  }
}



