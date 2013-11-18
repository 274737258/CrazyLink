/**********************************************************
 * ��Ŀ���ƣ�ɽկ������������Ϸ7�ս̳�
 * ��          �ߣ�֣����
 * ��Ѷ΢����SuperCube3D
 * ��          �ڣ�2013��10��
 * ��          ������Ȩ����   ��Ȩ�ؾ�
 * ��Դ���빩�����о�ѧϰOpenGL ES����AndroidӦ���ã�
 * ����ȫ���򲿷�������ҵ��;
 ********************************************************/

package elong.CrazyLink.Core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import elong.CrazyLink.CrazyLinkConstent;
import elong.CrazyLink.R;
import elong.CrazyLink.Control.CtlTip1;
import elong.CrazyLink.Draw.DrawAnimal;
import elong.CrazyLink.Draw.DrawAutoTip;
import elong.CrazyLink.Draw.DrawSingleScore;
import elong.CrazyLink.Draw.DrawTip1;
import elong.CrazyLink.Draw.DrawDisappear;
import elong.CrazyLink.Draw.DrawExchange;
import elong.CrazyLink.Draw.DrawFill;
import elong.CrazyLink.Draw.DrawGrid;
import elong.CrazyLink.Draw.DrawLoading;
import elong.CrazyLink.Draw.DrawScore;
import elong.CrazyLink.Interface.IControl;


public class ControlCenter {

	Context mContext;
	
	static int mPic[][];				//��Ӧ������ʾ��ͼƬ������DrawAnimal��Ⱦ
	static int mPicBak[][];				//mPic�ĸ���������autotip����
	
	static int mStatus[][];		//0������ʾ��1����ʾ���2:��ʾ������Ч��3:������Ч��4:������Ч
	
	static int mSingleScoreW = 0;	//��ʾ���ν�����λ��
	static int mSingleScoreH = 0;
	
    int animalTextureId;				//�����ز�����id
    int[] loadingTextureId = new int[10];			//���ض����ز�����id
    int gridTextureId;				//�����ز�����id
    int scoreTextureId;
    int congratulationTextureId;
    int fireTextureId;
    
    static int mAutoTipTimer = 0;			//�Զ���ʾ��ʱ��
    
	static public DrawAnimal drawAnimal;
	static public DrawLoading drawLoading;
	static public DrawGrid drawGrid;
	static public DrawExchange drawExchange;
	static public DrawDisappear drawDisappear;
	static public DrawFill drawFill;
	static public DrawScore drawScore;
	static public DrawSingleScore drawSingleScore;
	static public DrawTip1 drawTip1;
	static public DrawAutoTip drawAutoTip;

	
	static Score mScore;	//�������
	
	
	public static boolean mIsLoading = false;	//��ʾ���ڼ���
	public static boolean mIsAutoTip = false;	//�����Զ���ʾ״̬
	
	ArrayList<IControl> mControlList = new ArrayList<IControl>();	//��Ⱦ��Ŀ����б�
	
	static final int EFT_NONE  = 0;			//�հ�
	static final int EFT_NORMAL  = 1;		//������������Ч��
	static final int EFT_EXCHANGE  = 2;		//����Ч��
	static final int EFT_FILL  = 3;			//����Ч��
	static final int EFT_DISAPPEAR  = 4;		//����Ч��
	static final int EFT_AUTOTIP  = 5;		//�Զ���ʾЧ��


	public ControlCenter(Context context)
	{
		mContext = context;
		mScore = new Score();
	    mPic = new int[(int) CrazyLinkConstent.GRID_NUM][(int) CrazyLinkConstent.GRID_NUM];
	    mPicBak = new int[(int) CrazyLinkConstent.GRID_NUM][(int) CrazyLinkConstent.GRID_NUM];
	    mStatus = new int[(int) CrazyLinkConstent.GRID_NUM][(int) CrazyLinkConstent.GRID_NUM];
		init();
	}
	
	//��ʼ���߼�����֤��ʼ���Ժ��״̬û�д�������״̬��
	void init()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				mPic[i][j] = getRandom();
				while (isInLine(mPic, i,j))
				{
					mPic[i][j] = getRandom();	
				}
				mStatus[i][j] = EFT_NORMAL;
			}
		}
		
	}

	//����1~7�������
	static int getRandom()
	{
		int data = (int) (Math.random()*100);
		return (data % 7) + 1;
	}
	
	//ָ������(col,row)��X�����Ƿ��Ѿ�����
	static boolean isInLineX(int pic[][], int col, int row)
	{
		int picId = pic[col][row];
		if(0 == col)
		{
			if(picId == pic[col+1][row] && picId == pic[col+2][row])
			{
				return true;
			}
		}
		else if(1 == col)
		{
			if((picId == pic[col-1][row] && picId == pic[col+1][row])
					|| (picId == pic[col+1][row] && picId == pic[col+2][row]))
			{
				return true;
			}
		}
		else if(col > 1 && col < 5)
		{
			if((picId == pic[col-2][row] && picId == pic[col-1][row])
					|| (picId == pic[col-1][row] && picId == pic[col+1][row])
					|| (picId == pic[col+1][row] && picId == pic[col+2][row]))
			{
				return true;
			}
		}
		else if(5 == col)
		{
			if((picId == pic[col-2][row] && picId == pic[col-1][row])
					|| (picId == pic[col-1][row] && picId == pic[col+1][row]))
			{
				return true;
			}
		}
		else if(6 == col)
		{
			if(picId == pic[col-1][row] && picId == pic[col-2][row])
			{
				return true;
			}
		}
		return false;
	}
	
	//ָ������(col,row)��Y�����Ƿ��Ѿ�����
	static boolean isInLineY(int pic[][], int col, int row)
	{
		int picId = pic[col][row];
		if(0 == row)
		{
			if(picId == pic[col][row+1] && picId == pic[col][row+2])
			{
				return true;
			}
		}
		else if(1 == row)
		{
			if((picId == pic[col][row-1] && picId == pic[col][row+1])
					|| (picId == pic[col][row+1] && picId == pic[col][row+2]))
			{
				return true;
			}
		}
		else if(row > 1 && row < 5)
		{
			if((picId == pic[col][row-2] && picId == pic[col][row-1])
					|| (picId == pic[col][row-1] && picId == pic[col][row+1])
					|| (picId == pic[col][row+1] && picId == pic[col][row+2]))
			{
				return true;
			}
		}
		else if(5 == row)
		{
			if((picId == pic[col][row-2] && picId == pic[col][row-1])
					|| (picId == pic[col][row-1] && picId == pic[col][row+1]))
			{
				return true;
			}
		}
		else if(6 == row)
		{
			if(picId == pic[col][row-1] && picId == pic[col][row-2])
			{
				return true;
			}
		}
		return false;
	}

	//��������㷨��ֻҪX��Y������һ��������У�����Ϊ������������
	static boolean isInLine(int pic[][], int col, int row)
	{
		return isInLineX(pic, col, row) || isInLineY(pic, col, row);
	}

	//�����еĶ����ǳ���
	static void markInLine()
	{
		int markCount = 0;
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if (isInLine(mPic, i, j))	
				{
					mStatus[i][j] = EFT_DISAPPEAR;
					markCount++;
				}
			}
		}		
		if (markCount > 0)
		{
			drawDisappear.control.start();
			mScore.increase();
			mScore.increase(markCount);			
		}
		else
		{
			mScore.reset();
		}
	}
	
	//���������־
	static void clearInline()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				mStatus[i][j] = EFT_NORMAL;
			}
		}				
	}
	
	//������ɺ�Ҫ����Ӧ�ĸ�����Ϊ0
	static int clearPic()
	{
		int clearCount = 0;
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if (EFT_DISAPPEAR == mStatus[i][j]) 
				{
					mPic[i][j] = 0;
					clearCount++;
				}
			}
		}				
		return clearCount;
	}
	
	//�ж��Ƿ���Ҫ����������Ч
	static boolean isNeedClear()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if (isInLine(mPic, i,j))
				{
					return true;
				}
			}
		}						
		return false;
	}
	
	//��ǵ�����Ч
	static void fillGrid(int col, int row)
	{
		if(0 == mPic[col][row])
		{
			for(int i = row; i < (int)CrazyLinkConstent.GRID_NUM; i++)
			{
				mStatus[col][i] = EFT_FILL;
			}
		}
	}
	
	//ѭ�����Ҫ��䣨���䣩�ĸ���
	static void markFill()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				mStatus[i][j] = EFT_NORMAL;
			}
		}						
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				fillGrid(i,j);
			}
		}				
		fillMethod();
		drawFill.control.start();
	}
	
	//�Ƿ���Ҫ��䣨���䣩
	static boolean isNeedFill()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if(0 == mPic[i][j])
				{
					return true;
				}
			}
		}						
		return false;
	}
	
	//�����㷨
	static void fillMethod()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if(0 == mPic[i][j])		//0����ø����ǿյģ���Ҫ����
				{
					if(j < (int)CrazyLinkConstent.GRID_NUM - 1)
					{
						//����һ���е���
						mPic[i][j] = mPic[i][j + 1];
						mPic[i][j + 1] = 0;
					}
					else
					{
						//�������������ߵ�һ�У�������������䶯��
						mPic[i][j] = getRandom();	
					}
				}
			}
		}				
			
	}
	
	static void exchange(int pic[][], int col1, int row1, int col2, int row2)
	{
		//�Խ��������������Ч��У�飬�������Ч�ģ��򲻽��н���
		if(col1 < 0 || col1 > 6) return;
		if(col2 < 0 || col2 > 6) return;
		if(row1 < 0 || row1 > 6) return;
		if(row2 < 0 || row2 > 6) return;
		int picId = pic[col1][row1];
		pic[col1][row1] = pic[col2][row2];
		pic[col2][row2] = picId;
	}

    static void setSingleScorePosition(int col, int row)
    {
    	if(drawSingleScore.control.isRun()) return;
    	mSingleScoreW = col;
    	mSingleScoreH = row;
    }
    
    //ע����Ⱦ��Ŀ��ƶ��󵽿������ĵĿ����б�
    void controlRegister(IControl control)
    {
    	if(control != null)	mControlList.add(control);
    }
    
    //�Զ���ʾʶ���㷨
    //ֻ��Ҫ����һ�����ܳ��еģ���Ϊ�����Զ���ʾ����
    boolean autoTipMethod(int col, int row)
    {
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				exchange(mPicBak, i, j, i-1, j);
				if(isInLine(mPicBak, i, j)) return true;
				exchange(mPicBak, i-1, j, i, j);

				exchange(mPicBak, i, j, i+1, j);
				if(isInLine(mPicBak, i, j)) return true;
				exchange(mPicBak, i+1, j, i, j);

				exchange(mPicBak, i, j, i, j-1);
				if(isInLine(mPicBak, i, j)) return true;
				exchange(mPicBak, i, j-1, i, j);

				exchange(mPicBak, i, j, i, j+1);
				if(isInLine(mPicBak, i, j)) return true;
				exchange(mPicBak, i, j+1, i, j);
			}
		}
    	return false;
    }

    //�Զ���ʾ
    void autoTip()
    {	
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				mPicBak[i][j] = mPic[i][j];
			}
		}

		for(int i = 1; i < (int)CrazyLinkConstent.GRID_NUM - 1; i++)
		{
			for(int j = 1; j < (int)CrazyLinkConstent.GRID_NUM - 1; j++) 
			{
				if(autoTipMethod(i, j))
				{
					markAutoTip();
					return;
				}
			}
		}
    }
    
	//�������Զ���ʾ�Ķ����ʶ����
	static void markAutoTip()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if (isInLine(mPicBak, i, j))	
				{
					mStatus[i][j] = EFT_AUTOTIP;
				}
			}
		}		
	}
    
	//���Զ���ʾ��ʶ���
	static void clearAutoTip()
	{
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++) 
			{
				if (EFT_AUTOTIP == mStatus[i][j])	
				{
					mStatus[i][j] = EFT_NORMAL;
				}
			}
		}				
		mIsAutoTip = false;
		mAutoTipTimer = 0;
	}

	
	public void draw(GL10 gl)
	{
		drawLoading.draw(gl); 
		if(mIsLoading) return;
		
		drawScore.draw(gl,mScore.getScore());
		drawSingleScore.draw(gl, mSingleScoreW, mSingleScoreH, mScore.getAward());
		drawAutoTip.control.start();
		drawTip1.draw(gl);
		for(int i = 0; i < (int)CrazyLinkConstent.GRID_NUM; i++)
		{
			for(int j = 0; j < (int)CrazyLinkConstent.GRID_NUM; j++)
			{
				switch (mStatus[i][j])
				{
				case EFT_NORMAL:	//������ʾ
					drawAnimal.draw(gl,mPic[i][j],i,j);
					break;
				case EFT_EXCHANGE:	//������Ч
					drawExchange.draw(gl);		
					break;
				case EFT_FILL:	//������Ч
					drawFill.draw(gl, mPic[i][j], i, j);
					break;
				case EFT_DISAPPEAR:	//������Ч
					drawDisappear.draw(gl, mPic[i][j], i, j);
					break;
				case EFT_AUTOTIP:	//�Զ���ʾ��Ч
					drawAutoTip.draw(gl, i, j);
					drawAnimal.draw(gl,mPic[i][j],i,j);
					break;
				}				                  
			}
		}
    	drawGrid.draw(gl);
		
	}
	//��ʼ���������
	public void initTexture(GL10 gl)
	{
    	animalTextureId = initTexture(gl, R.drawable.animal);	//��ʼ���������    	
    	for(int i = 0; i < 10; i++)
    	{
    		loadingTextureId[i] = initTexture(gl, R.drawable.loading_01 + i);
    	}    	
    	gridTextureId = initTexture(gl, R.drawable.grid);
    	scoreTextureId = initTexture(gl, R.drawable.number);
    	congratulationTextureId = initTexture(gl, R.drawable.word);
    	fireTextureId = initTexture(gl, R.drawable.autotip);
	}
	
	//��ʼ����Ⱦ����
	public void initDraw(GL10 gl)
	{
    	drawAnimal = new DrawAnimal(animalTextureId);			//���������زĶ���    	
    	drawGrid = new DrawGrid(gridTextureId);					//���������زĶ���
    	drawDisappear = new DrawDisappear(drawAnimal);
    	drawFill = new DrawFill(drawAnimal);
    	drawScore = new DrawScore(scoreTextureId);
    	drawSingleScore = new DrawSingleScore(gl);
    	drawTip1 = new DrawTip1(congratulationTextureId);
    	drawLoading = new DrawLoading(loadingTextureId);		//�������ض����ز�
    	drawExchange = new DrawExchange(drawAnimal);
    	drawAutoTip = new DrawAutoTip(fireTextureId);
    
    	//����Ⱦ��Ŀ��ƶ���ע�ᵽ���������б�
    	controlRegister(drawDisappear.control);
    	controlRegister(drawExchange.control);
    	controlRegister(drawFill.control);
    	controlRegister(drawLoading.control);
    	controlRegister(drawSingleScore.control);
    	controlRegister(drawTip1.control);
    	controlRegister(drawAutoTip.control);
	}

	//��ʼ������ķ���
	private int initTexture(GL10 gl, int drawableId)
	{
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		int currTextureId = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);//ָ����С���˷���
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);//ָ���Ŵ���˷���
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);//ָ��S��������ͼģʽ
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);//ָ��T��������ͼģʽ
		InputStream is = mContext.getResources().openRawResource(drawableId);
		Bitmap bitmapTmp;
		try{
			bitmapTmp = BitmapFactory.decodeStream(is);
		}
		finally{
			try{
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
		bitmapTmp.recycle();
		return currTextureId;
	}

	
	//��Ϣ����
	public static final int EXCHANGE_START = 1;
	public static final int EXCHANGE_END = 2;
	public static final int LOADING_START = 3;
	public static final int LOADING_END = 4;
	public static final int DISAPPEAR_END = 5;
	public static final int FILL_END = 6;


	
	//��Ϣ����
    public static Handler mHandler = new Handler(){   
        @Override  
		public void handleMessage(Message msg) 
		{
			    // process incoming messages here
			switch(msg.what)
			{
			case EXCHANGE_START:
			{
				clearAutoTip();
				Bundle b = msg.getData();
				int col1 = b.getInt("col1");
				int col2 = b.getInt("col2");
				int row1 = b.getInt("row1");
				int row2 = b.getInt("row2");				
		    	mStatus[col1][row1] = EFT_EXCHANGE;			//���ڽ���״̬
		    	mStatus[col2][row2] = EFT_NONE;
		    	setSingleScorePosition(col1, row1);
		    	drawExchange.init(mPic[col1][row1], col1, row1, mPic[col2][row2], col2, row2);
				break;
			}
			case EXCHANGE_END:
			{
				Bundle b = msg.getData();
				int col1 = b.getInt("col1");
				int col2 = b.getInt("col2");
				int row1 = b.getInt("row1");
				int row2 = b.getInt("row2");
				exchange(mPic, col1, row1, col2, row2);
		    	mStatus[col1][row1] = EFT_NORMAL;			//����״̬���
		    	mStatus[col2][row2] = EFT_NORMAL;
				
				markInLine();				
				break;
			}
			case LOADING_START:		    	
		    	drawLoading.control.start();
		    	break;
			case LOADING_END:
				mIsLoading = false;
				break;			
			case DISAPPEAR_END:
			{
				int clearCnt = clearPic();
				mScore.award(clearCnt);
				if(mScore.getAward() > 0)
				{
					CtlTip1 ctl = (CtlTip1) drawTip1.control;
					ctl.init(clearCnt);
					drawSingleScore.control.start();
				}
				clearInline();
				markFill();
				break;
			}
			case FILL_END:				
				if(isNeedFill())
				{
					markFill();
				}
				else
				{
					if(isNeedClear())
					{
						markInLine();
					}
				}
				clearAutoTip();
				break;
			}
		}
    };
    
    
    //�������ĵĶ���ִ��
    public void run()
    {
    	mAutoTipTimer++;
    	if(mAutoTipTimer > CrazyLinkConstent.AUTOTIP_DELAY)
    	{
    		if(!mIsAutoTip)
    		{
    			mIsAutoTip = true;
    			autoTip();
    		}
    	}
		IControl control = null;		
		for(int i=0;i<mControlList.size();i++){ 
			control = mControlList.get(i);
			control.run();
		}
    }
    
}
