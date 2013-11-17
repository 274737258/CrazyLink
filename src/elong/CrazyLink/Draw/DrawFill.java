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
import elong.CrazyLink.CrazyLinkConstent;
import elong.CrazyLink.Control.CtlFill;
import elong.CrazyLink.Interface.IControl;

public class DrawFill {

	DrawAnimal drawAnimal;
	int mWitch1 = 0;
	int mWitch2 = 0;
	
	int mCol1 = 0;
	int mCol2 = 0;
	int mRow1 = 0;
	int mRow2 = 0;
	
	public IControl control;
	
	public DrawFill(DrawAnimal drawAnimal)
	{
		this.drawAnimal = drawAnimal;
		
		control = new CtlFill();
	}
	
	public void draw(GL10 gl, int witch, int col, int row)
	{
		//if(!control.isRun()) return;
		CtlFill ctl = (CtlFill)control;
		gl.glPushMatrix();		
		gl.glTranslatef(0f, ctl.getY()*CrazyLinkConstent.translateRatio, 0f);
		drawAnimal.draw(gl, witch, col, row);
		gl.glPopMatrix();
	}
}
