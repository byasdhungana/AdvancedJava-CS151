
import java.lang.reflect.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
public class shapetester {
	public static DShape makeShapeFromModel(DShapeModel model)
	{
		DShape shape = null;
		if (model instanceof DTextModel)
			shape = new DText();
		else if (model instanceof DOvalModel)
			shape = new DOval();
		else if (model instanceof DRectModel)
			shape = new DRect();
		else if (model instanceof DLineModel)
			shape = new DLine();
		shape.setModel(model);
		return shape;
	}
	
	
	public static void main(String[] args)
	   {
		shapetester st =new shapetester();
//		This one is for rectangle
	      DShapeModel R = new DRectModel();
	      DShape k = makeShapeFromModel(R);
	      k.draw();
	      System.out.println(k.getShape()+" expected: x=10,y=20,w=20,height=20,fill=0x808080ff");
	      System.out.println(R.getX()+ ","+R.getY() +","+R.getWidth() + ","+ R.getHeight()+
	    		  " expected: 10,10,20,20");
//		this one is for oval	      
	      
	      
	      DOvalModel O = new DOvalModel();
	      DShape O_SHAPE = makeShapeFromModel(O);
	      O_SHAPE.draw();
	      System.out.println(O_SHAPE.getShape()+"expected: eclipse");
	      System.out.println(O.getXCenter()+ ","+O.getYCenter() +","+O.getXRadius() + ","+ O.getYRadius()+"\" expected: 20,20,10,10");
	      
	      
	      
//		this one is for line      
	     
	      DLineModel Line = new DLineModel();
		 DShape LINE_SHAPE = makeShapeFromModel(Line);
		 LINE_SHAPE.draw();
		 System.out.println(Line.getStartX()+ ","+Line.getStartY()+","+Line.getEndX()+","+Line.getEndY()+" Expected: 10 10 30 30");
		 LINE_SHAPE.moveBy(3.0,4.0);
		 System.out.println(Line.getStartX()+ ","+Line.getStartY()+","+Line.getEndX()+","+Line.getEndY()+"Expected: 13.0,14.0,33.0,34.0");
		 System.out.println(Line.getStart()+" expected: 13,14");
		 System.out.println(Line.getEnd()+" expected: 33,34");
		 System.out.println(Line.getStartX()+" expected: 13");
		 System.out.println(Line.getStartY()+"expected: 14");
		 System.out.println(Line.getEndX()+"expected:33");
		 System.out.println(Line.getEndY()+"expected:34");
		 System.out.println(Line.getColor()+"expected:rgb(128,128,128)");
		 System.out.println("Before:"+Line.getStartX()+ ","+Line.getStartY()+","+Line.getEndX()+","+Line.getEndY());
		 //invokation by refllection
		 st.callSetter(Line,"startX",2);
		 Line.setStartY(3);
		 Line.setEndX(4);
		 Line.setEndY(5);
		 System.out.println("After:"+Line.getStartX()+ ","+Line.getStartY()+","+Line.getEndX()+","+Line.getEndY());
		 
// this one is for text		 
		 
	      DTextModel Txt = new DTextModel();
	      System.out.println(Txt.getText());
	      Txt.setText("Hi my name is param");
	      System.out.println(Txt.getText()+" expected: Hi my name is param");
		  System.out.println(Txt.getFont());
		  Txt.setColor(Color.green);
		  System.out.println(Txt.getColor());
		  DText TXT_SHAPE = (DText)makeShapeFromModel(Txt);
		  TXT_SHAPE.draw();
	      System.out.println(TXT_SHAPE.getText());
	      System.out.println(TXT_SHAPE.getFont());
	      System.out.println(TXT_SHAPE.getShape());
	      
	      
// 
	      getFieldNamesOfModel();
	      
	      
	   }
//As we did not have method for reflection we used reflection for getting all fields of class for showing our understanding of reflection
	public static void getFieldNamesOfModel() {
	          Object txtmodel = new DShapeModel();
	          Field[] fields = txtmodel.getClass().getDeclaredFields();
	       
	          for (Field field : fields) {
	              System.out.println("This are my fields "+field+"\n");
	          }
	       
	      }
	/**
	  * reflection setter call
	  * @param obj
	  * @param fieldName
	  * @param value
	  */
	 private void callSetter(Object obj, String fieldName, Object value){
	  PropertyDescriptor pd;
	  try {
	   pd = new PropertyDescriptor(fieldName, obj.getClass());
	   pd.getWriteMethod().invoke(obj, value);
	  } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	   // TODO Auto-generated catch block
	   ((Throwable) e).printStackTrace();
	  }
	 }
	
}
