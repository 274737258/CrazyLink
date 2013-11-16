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

import android.os.Bundle;
import android.os.Message;
import elong.CrazyLink.Core.ControlCenter;
import elong.CrazyLink.Interface.IControl;

public class CtlExchange implements IControl{
	int mCol1 = 0;					//����ֵ
	int mCol2 = 0;
	int mRow1 = 0;
	int mRow2 = 0;
	
	int mDeltaX1 = 0;				//ƫ����
	int mDeltaY1 = 0;
	int mDeltaX2 = 0;
	int mDeltaY2 = 0;
	
	int mStep = 10;				//ƫ�Ʋ���
	boolean mDirectionX = true;   //�˶��������
	boolean mDirectionY = true;
	boolean mNeedMoveX = false;	//�Ƿ���Ҫƫ��
	boolean mNeedMoveY = false;
	
	boolean mStop = false;			//�Ƿ�ֹͣ״̬

	public void init(int col1, int row1, int col2, int row2)
	{
		mNeedMoveX = false;
		mNeedMoveY = false;
		mCol1 = col1;
		mCol2 = col2;
		mRow1 = row1;
		mRow2 = row2;

		if(col1 == col2)
		{
			mDeltaX1 = 0;
			mDeltaX2 = 0;
		}
		else
		{
			//��������λ���������˶��ĳ�ʼ����
			mNeedMoveX = true;
			if(col1 >  col2)
			{
				mDirectionX = true;
				mDeltaX1 = 40;
			}
			else
			{
				mDirectionX = false;
				mDeltaX1 = -40;
			}
		}
		if(row1 == row2)
		{
			mDeltaY1 = 0;
			mDeltaY2 = 0;
		}
		else
		{
			//��������λ���������˶��ĳ�ʼ����			
			mNeedMoveY = true;
			if(row1 >  row2)
			{
				mDirectionY = true;
				mDeltaY1 = 40;
			}
			else
			{
				mDirectionY = false;
				mDeltaY1 = -40;
			}
		}
		mStop = false;		
	}
	
	public void run()
	{
		if(mStop) return;
		//��ƫ�Ʒ�Χ�޶���-0.5~0.5�䣬����ָ�����伴��ͷ
		if(mNeedMoveX)
		{
			if(mDeltaX1 >= 50)
			{
				mDirectionX = true;
				mStop = true;
			}
			else if (mDeltaX1 <= -50)
			{
				mDirectionX = false;
				mStop = true;
			}
			if(mDirectionX)
			{
				mDeltaX1 -= mStep;
				mDeltaX2 = -mDeltaX1;
			}
			else
			{
				mDeltaX1 += mStep;
				mDeltaX2 = -mDeltaX1;
			}			
		}

		if(mNeedMoveY)
		{
			if(mDeltaY1 >= 50)
			{
				mDirectionY = true;
				mStop = true;
			}
			else if (mDeltaY1 <= -50)
			{
				mDirectionY = false;
				mStop = true;
			}
			if(mDirectionY)
			{
				mDeltaY1 -= mStep;
				mDeltaY2 = -mDeltaY1;
			}
			else
			{
				mDeltaY1 += mStep;
				mDeltaY2 = -mDeltaY1;
			}			
		}
		
		if(mStop)
		{
			Bundle b = new Bundle();
			b.putInt("col1", mCol1);
			b.putInt("row1", mRow1);
			b.putInt("col2", mCol2);
			b.putInt("row2", mRow2);
			Message msg = new Message();
		    msg.what = ControlCenter.EXCHANGE_END;
			msg.setData(b);
		    ControlCenter.mHandler.sendMessage(msg);
		}

	}
	
	public float getX1()
	{
		float delta = 0;
		//���ݸ����������ó�ʼƫ���������˶���Χ������0~1��0~-1����
		if(mCol1 > mCol2)
		{
			delta = -0.5f; 
		}
		else if(mCol1 < mCol2)
		{
			delta = 0.5f;
		}
		return delta + mDeltaX1/100.0f;
	}
	
	public float getX2()
	{
		float delta = 0;
		//���ݸ����������ó�ʼƫ���������˶���Χ������0~1��0~-1����
		if(mCol1 > mCol2)
		{
			delta = 0.5f; 
		}
		else if(mCol1 < mCol2)
		{
			delta = -0.5f;
		}
		return delta + mDeltaX2/100.0f;
	}

	public float getY1()
	{
		float delta = 0;
		//���ݸ����������ó�ʼƫ���������˶���Χ������0~1��0~-1����
		if(mRow1 > mRow2)
		{
			delta = -0.5f; 
		}
		else if(mRow1 < mRow2)
		{
			delta = 0.5f;
		}
		return delta + mDeltaY1/100.0f;
	}

	public float getY2()
	{
		float delta = 0;
		//���ݸ����������ó�ʼƫ���������˶���Χ������0~1��0~-1����
		if(mRow1 > mRow2)
		{
			delta = 0.5f; 
		}
		else if(mRow1 < mRow2)
		{
			delta = -0.5f;
		}
		return delta + mDeltaY2/100.0f;
	}
	
	public boolean isRun()
	{
		return !mStop;
	}
}
