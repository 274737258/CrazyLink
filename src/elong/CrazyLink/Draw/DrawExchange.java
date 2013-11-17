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
import elong.CrazyLink.Control.CtlExchange;
import elong.CrazyLink.Interface.IControl;

public class DrawExchange {

	DrawAnimal drawAnimal;
	int mWitch1 = 0;
	int mWitch2 = 0;
	
	int mCol1 = 0;
	int mCol2 = 0;
	int mRow1 = 0;
	int mRow2 = 0;
	
	public IControl control;
	
	public DrawExchange(DrawAnimal drawAnimal)
	{
		this.drawAnimal = drawAnimal;
		mWitch1 = 0;
		mWitch2 = 0;
		mCol1 = 0;
		mCol2 = 0;
		mRow1 = 0;
		mRow2 = 0;		
		control = new CtlExchange();
	}
	
	public void init(int witch1, int col1, int row1, int witch2, int col2, int row2)
	{
		mWitch1 = witch1;
		mWitch2 = witch2;
		mCol1 = col1;
		mCol2 = col2;
		mRow1 = row1;
		mRow2 = row2;		
		((CtlExchange)control).init(col1, row1, col2, row2);
	}
	
	public void draw(GL10 gl)
	{
		CtlExchange ctl = (CtlExchange) control;
		if(!control.isRun()) return;
		gl.glPushMatrix();		
		gl.glTranslatef(ctl.getX1()*CrazyLinkConstent.translateRatio, ctl.getY1()*CrazyLinkConstent.translateRatio, 0f);
		drawAnimal.draw(gl, mWitch1, mCol1, mRow1);
		gl.glPopMatrix();
		gl.glPushMatrix();		
		gl.glTranslatef(ctl.getX2()*CrazyLinkConstent.translateRatio, ctl.getY2()*CrazyLinkConstent.translateRatio, 0f);
		drawAnimal.draw(gl, mWitch2, mCol2, mRow2);
		gl.glPopMatrix();		
	}
}
