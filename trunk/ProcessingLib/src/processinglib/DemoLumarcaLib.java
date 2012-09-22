package processinglib;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import lumarca.cache.LumarcaCacher;
import lumarca.cache.LumarcaFrame;
import lumarca.cache.LumarcaSequence;
import lumarca.lineMap.Line;
import lumarca.obj.ObjFile;
import lumarca.obj.Shape;
import lumarca.util.ProcessingObject;
import processing.core.PApplet;
import processing.core.PVector;
import template.library.LumarcaLibrary;

public class DemoLumarcaLib extends PApplet {

	LumarcaLibrary lumarca;

	private float waveRot = 0.0f;
	
	Shape shape;
	
	LumarcaSequence frames;

	public void setup() {

		ProcessingObject.setPApplet(this);

		size(1024, 1536, OPENGL);

		
		lumarca = new LumarcaLibrary(this, "lineMap1024.txt");
//		lumarca = new LumarcaLibrary(this, width*4, true);

		PVector center = new PVector();
		
		lumarca.getCenterPosition(center);
//		
//		PVector max = new PVector();
//		lumarca.getMaxPosition(max);
//
		shape = new ObjFile(this, center,
				new PVector(0.1f, 0.1f, 0.1f), "obj/male head.obj",
				20f);

//		shape.center.z = lumarca.getMidZ();
//		shape.center.y = lumarca.getMidY();
//
		shape.rotateOnZ(PI);
//		shape.rotateOnY(PI/2);
		
		if(true){
			for(float f = 0; f < TWO_PI; f+=0.1f){
	//			shape.center.x+=10f
				System.out.println(f);
				shape.rotateOnY(0.1f);
				LumarcaCacher.addFrame(new LumarcaFrame(lumarca.getShapeLines(shape)));
			}
	//		
			LumarcaCacher.saveFrames("test4k.txt");
		}
		
		frames = LumarcaCacher.loadFrames("test4k.txt");
//		
//		System.out.println("frames: " + frames.size());
		
		frameRate(90);
	}

	boolean rot = false;
	
	public void draw() {
		frames.draw();
		

//		lumarca.calibration();
////		
		if(rot)
			waveRot += 0.0001f;
		
		lumarca.moveCamera(waveRot * 300);
		
//		Line[] lines = lumarca.getLines();
//
//		for (int lineNum = 0; lineNum < lines.length; lineNum++) {
//
//			PVector coord = lines[lineNum].bottom;
//
//			lumarca.drawLine(
//							new PVector(coord.x / 1024f, 1f,
//									1024f - coord.x / 1024f),
//							new PVector(coord.x, coord.y
//									- PApplet.sin(lumarca.lib.lineMap.projectorX
//											- coord.x / 100 + waveRot * 50)
//									* 150 - 200, coord.z),
//							new PVector(coord.x, coord.y
//									- PApplet.sin(lumarca.lib.lineMap.projectorX
//											- coord.x / 100 + waveRot * 50)
//									* 150 - 150, coord.z));
//
//		}

//		lumarca.drawWireFrame(shape);
//		lumarca.drawShape(new PVector(1, 1, 0), shape);

//		GL gl = lumarca.lib.gl;
//		
//		gl.glColor3f(1f,1f,1f);
//
//		gl.glBegin(GL.GL_LINE_STRIP);
//		
//		gl.glVertex3f(width/2, 0, -100);
//		gl.glVertex3f(width/2, height, -100);
//		
//		gl.glEnd();
//		
//		fill(255, 0, 0);
//		ellipse(0, 0, 50, 50);
//		ellipse(width, height, 50, 50);
//		ellipse(0, height*2, 50, 50);
	}
	
	public void keyPressed(){
		rot = !rot;
		waveRot = 0.000f;
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { DemoLumarcaLib.class.getName() });
	}
}