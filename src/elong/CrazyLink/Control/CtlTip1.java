
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

import elong.CrazyLink.CrazyLinkConstent.E_SOUND;
import elong.CrazyLink.Core.ControlCenter;

public class CtlTip1 extends CtlBase{
	
	int mDeltaW = 0;
	int mDeltaH = 0;
	int mDeltaX = 0;
	int mDeltaY = 0;
	int mStep = 0;
	int mKeep = 0;
	int mPicId = 0;
	int mGoodCnt = 0;
	
	public void run()
	{
		int maxW = 96;
		int minW = 16;
		int deltaW = 8;
		int deltaH = 2;
		if(!mStop)
		{
			if(0 == mStep)
			{
				mDeltaW += deltaW;
				mDeltaH += deltaH;
				mDeltaX ++;
				mDeltaY ++;
				if (mDeltaW >= maxW)
				{
					mStep = 1;
				}
			}
			else if(1 == mStep)
			{
				mKeep++;
				if(mKeep >= 10)
				{
					mKeep = 0;
					mStep = 2;
				}				
			}
			else if(2 == mStep)
			{
				mDeltaW -= deltaW;
				mDeltaH -= deltaH;
				mDeltaX --;
				mDeltaY --;
				if (mDeltaW <= minW)
				{
					mStep = 3;
				}				
			}
			else if(3 == mStep)
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
	
	public void init(int clearCnt)
	{
		if(clearCnt < 3) return;
		if(!mStop) return;
		mDeltaW = 0;
		mDeltaH = 0;
		mDeltaX = 0;
		mDeltaY = 0;
		mStep = 0;
		
		if(clearCnt >6) clearCnt = 6;
		mPicId = clearCnt - 3;
		if(3 == clearCnt)
		{
			mGoodCnt++;
			if(1 == mGoodCnt % 5)
			{
				super.start();      //��������5��3���������ʾһ��GOOD
				ControlCenter.mSound.play(E_SOUND.GOOD);
			}
		}
		else
		{
			//������ÿ�ζ���ʾ
			super.start();
		}
	}
		
	public int getPicId()
	{
		return mPicId;
	}
}
