
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

public class CtlTip1 implements IControl{
	
	int mDeltaW = 0;
	int mDeltaH = 0;
	int mDeltaX = 0;
	int mDeltaY = 0;
	int mDirection = 0;
	int mKeep = 0;
	int mPicId = 0;
	boolean mStop = true;
	
	public void run()
	{
		int maxW = 96;
		int minW = 16;
		int deltaW = 8;
		int deltaH = 2;
		if(!mStop)
		{
			if(0 == mDirection)
			{
				mDeltaW += deltaW;
				mDeltaH += deltaH;
				mDeltaX ++;
				mDeltaY ++;
				if (mDeltaW >= maxW)
				{
					mDirection = 1;
				}
			}
			else if(1 == mDirection)
			{
				mKeep++;
				if(mKeep >= 10)
				{
					mKeep = 0;
					mDirection = 2;
				}				
			}
			else if(2 == mDirection)
			{
				mDeltaW -= deltaW;
				mDeltaH -= deltaH;
				mDeltaX --;
				mDeltaY --;
				if (mDeltaW <= minW)
				{
					mDirection = 3;
				}				
			}
			else if(3 == mDirection)
			{
				mStop = true;
			}
		}		
	}
	
	public int getW()
	{
		return mDeltaW;
	}
	
	public int getH()
	{
		return mDeltaH;
	}
	
	public int getX()
	{
		return mDeltaX;
	}
	
	public int getY()
	{
		return mDeltaY;
	}
	
	public void start(int clearCnt)
	{
		if(clearCnt < 3) return;
		if(!mStop) return;
		mDeltaW = 0;
		mDeltaH = 0;
		mDeltaX = 0;
		mDeltaY = 0;
		mStop = false;
		mDirection = 0;
		
		if(clearCnt >6) clearCnt = 6;
		mPicId = clearCnt - 3;		
	}
	
	public boolean isRun()
	{
		return !mStop;
	}
	
	public int getPicId()
	{
		return mPicId;
	}
}
