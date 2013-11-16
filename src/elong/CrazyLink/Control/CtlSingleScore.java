
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

import elong.CrazyLink.Interface.IControl;

public class CtlSingleScore implements IControl{
	
	int mDeltaY = 0;
	int mDelta = 1;
	int mCount = 0;
	boolean mStop = true;
	
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
		if(!mStop) return;
		mDeltaY = 0;
		mCount = 0;
		mStop = false;		
	}
	
	public boolean isRun()
	{
		return !mStop;
	}
	
}

