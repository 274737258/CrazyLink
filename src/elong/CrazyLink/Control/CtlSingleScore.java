
/**********************************************************
 * ��Ŀ���ƣ�ɽկ������������Ϸ7�ս̳�
 * ��          �ߣ�֣����
 * ��Ѷ΢����SuperCube3D
 * ��          �ڣ�2013��10��
 * ��          ������Ȩ����   ��Ȩ�ؾ�
 * ��Դ���빩�����о�ѧϰOpenGL ES����AndroidӦ���ã�
 * ����ȫ���򲿷�������ҵ��;
 ********************************************************/
package elong.CrazyLink.Control;

public class CtlSingleScore extends CtlBase{
	
	int mDeltaY = 0;
	int mDelta = 1;
	int mCount = 0;
	
	public void run()
	{
		if(!mStop)
		{
			mCount++;
			
			if (mCount > 30) mStop = true;
			if (mCount < 20) mDeltaY += mDelta; 
		}		
	}
	
	
	public int getY()
	{
		return mDeltaY;
	}
	
	public void start()
	{
		mDeltaY = 0;
		mCount = 0;
		super.start();
	}
		
}

