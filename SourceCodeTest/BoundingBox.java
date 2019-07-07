package s17cs350task1;

import javafx.geometry.*;
import java.util.*;

public class BoundingBox
extends java.lang.Object
implements java.lang.Cloneable{
   /*Author: Cameron Hilsmann 
    */
    
    private Point3D center;
    private Dimension3D size;
    
    //NESTED CLASS
   public static enum E_Plane
   {
      XY,
      XZ,
      YZ;
   }//end nested class
   //END OF NESTED CLASS
   
   public BoundingBox(Point3D center, Dimension3D size)
   {
      //WORK-IN-PROGRESS
      if(center == null || size == null){throw new TaskException("Parameters are null");}
      else{
         this.center = center;
         this.size = size;
      }
   }//end constructor
   
   public double calculateArea(BoundingBox.E_Plane plane)
   {
      double width;
      double height;
      double depth;
      if(plane == null) {throw new TaskException("Plane is null");}
      else{
         if(plane == E_Plane.XY){
            width = size.getWidth();
            height = size.getHeight();
            return 2*(width*height);
         }//end first check
         else if(plane == E_Plane.YZ){
            height = size.getHeight();
            depth = size.getDepth();
            return 2*(height*depth);
         }//end second check
         else{
            width = size.getWidth();
            depth = size.getDepth();
            return 2*(width*depth);
         }
      }
   }//end calculateArea
   
   
   public double calculateVolume()
   {
      double x = size.getWidth();
      double y = size.getHeight();
      double z = size.getDepth();
      
      return x*y*z;
   }//end calculateVolume
   
   public BoundingBox clone()throws java.lang.CloneNotSupportedException
   {
      BoundingBox copy = (BoundingBox)super.clone();
      
      copy.center = getCenter();
      copy.size = getSize();
   
      return copy;
   }//end clone
   
   public BoundingBox extend(BoundingBox boundingBox)
   {
      
     List<Point3D> b1Corners = this.generateCorners();
     List<Point3D> b2Corners = boundingBox.generateCorners();


      double XMin = 0.0;
      double XMax = 0.0;
      double YMin = 0.0;
      double YMax = 0.0;
      double ZMin = 0.0;
      double ZMax = 0.0;


        XMin = Math.min(b1Corners.get(3).getX(),b2Corners.get(3).getX()); //bLeftNear Check
        XMax = Math.max(b1Corners.get(2).getX(),b2Corners.get(2).getX()); //bRightNear check
        YMin = Math.min(b1Corners.get(3).getY(),b2Corners.get(3).getY()); //bLeftNear Check
        YMax = Math.max(b1Corners.get(6).getY(),b2Corners.get(6).getY()); // topRightNear Check
        ZMin = Math.min(b1Corners.get(0).getZ(),b2Corners.get(0).getZ()); // bLeftFar check
        ZMax = Math.max(b1Corners.get(3).getZ(), b2Corners.get(3).getZ());// bLeftNear Check


      return new BoundingBox(new Point3D((XMax + XMin) / 2.0 ,(YMax + YMin) /2.0,(ZMax + ZMin) / 2.0), new Dimension3D(XMax - XMin,YMax - YMin,ZMax - ZMin));

   }//emd extend
   
   public java.util.List<Point3D> generateCorners()
   {
      ArrayList<Point3D> corners = new ArrayList<Point3D>();
      double halfW, halfH, halfD;
      
      halfW = size.getWidth() / 2; //X
      halfH = size.getHeight() / 2; //Y
      halfD = size.getDepth() / 2; //Z
      
      center = this.center;

       //bottomLeftFar
       Point3D bLeftFar = new Point3D(center.getX() - halfW, center.getY() - halfH, center.getZ() - halfD);

       //BottomRightFar
       Point3D bRightFar = new Point3D(center.getX() + halfW, center.getY() - halfH, center.getZ() - halfD);

       //BOTTOMRIGHTNEAR
       Point3D bRightNear = new Point3D(center.getX() + halfW, center.getY() - halfH, center.getZ() + halfD);
      
      //bottomleftnear
      Point3D bLeftNear = new Point3D(center.getX() - halfW, center.getY() - halfH, center.getZ() + halfD);

       //TOPLEFTFAR
       Point3D tLeftFar = new Point3D(center.getX() - halfW, center.getY() + halfH, center.getZ() - halfD);

       //TopRightFar
       Point3D tRightFar = new Point3D(center.getX() + halfW, center.getY() + halfH, center.getZ() - halfD);

       //TopRightNear
       Point3D tRightNear = new Point3D(center.getX() + halfW, center.getY() +halfH, center.getZ() + halfD);
      
      //TOPLEFTNEAR
      Point3D tLeftNear = new Point3D(center.getX() - halfW, center.getY() + halfH, center.getZ() + halfD);

      
      //ADD TO ARRAY
      corners.add(bLeftFar);
      corners.add(bRightFar);
      corners.add(bRightNear);
      corners.add(bLeftNear);
      corners.add(tLeftFar);
      corners.add(tRightFar);
      corners.add(tRightNear);
      corners.add(tLeftNear);
      
      return corners;
   }//end generateeCorners
   
   public Point3D getCenter()
   {  
      return this.center;
   
   }//end getCenter
   
   public Dimension3D getSize()
   {
      return this.size;
   }//end getSize
   
  

}//end class