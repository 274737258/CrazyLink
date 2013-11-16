/**********************************************************
 * ��Ŀ���ƣ�ɽկ������������Ϸ7�ս̳�
 * ��          �ߣ�֣����
 * ��Ѷ΢����SuperCube3D
 * ��          �ڣ�2013��10��
 * ��          ������Ȩ����   ��Ȩ�ؾ�
 * ��Դ���빩�����о�ѧϰOpenGL ES����AndroidӦ���ã�
 * ����ȫ���򲿷�������ҵ��;
 ********************************************************/


package elong.CrazyLink.Draw;
import javax.microedition.khronos.opengles.GL10;

import elong.CrazyLink.Control.CtlDisappear;

public class DrawDisappear {

	DrawAnimal drawAnimal;
	
	public CtlDisappear control;			//��̬����Ч��
	
	public DrawDisappear(DrawAnimal drawAnimal)
	{
		this.drawAnimal = drawAnimal;
		
		control = new CtlDisappear();
	}
	
	public void draw(GL10 gl, int witch, int col, int row)
	{			
		if(0 == control.getCount() % 2)	//����ʵ������	
		{
			gl.glPushMatrix();
			drawAnimal.draw(gl, witch, col, row);
			gl.glPopMatrix();
		}		
	}
	
}
