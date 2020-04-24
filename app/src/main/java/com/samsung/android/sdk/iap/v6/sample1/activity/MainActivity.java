package com.samsung.android.sdk.iap.v6.sample1.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.android.sdk.iap.lib.helper.HelperDefine.OperationMode;
import com.samsung.android.sdk.iap.lib.helper.IapHelper;
import  com.samsung.android.sdk.iap.v6.sample1.adapter.OwnedListAdapter;
import  com.samsung.android.sdk.iap.v6.sample1.adapter.PaymentAdapter;
import  com.samsung.android.sdk.iap.v6.sample1.constants.ItemDefine;
import  com.samsung.android.sdk.iap.v6.sample1.constants.ShardPrefConstants;
import com.samsung.android.sdk.iap.v6.sample1.R;

public class MainActivity extends Activity
{
    private final String TAG = MainActivity.class.getSimpleName();
    private static OperationMode IAP_MODE = OperationMode.OPERATION_MODE_PRODUCTION;

    private IapHelper  mIapHelper = null;
    private PaymentAdapter mPaymentAdapter = null;
    private OwnedListAdapter mOwnedListAdapter = null;

    //Variable for UI element
    private int mBulletCnt = 0;
    private int mGunLevel = 0;
    private boolean mInfiniteBullet = false;

    private TextView mTextBulletCnt = null;
    private TextView mTextMaxBullet = null;
    private TextView mTextInfiniteBullet = null;

    private ImageView mGunImage1 = null;
    private ImageView mGunImage2 = null;
    private ImageView mShotImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main);
        getPreferences();

        mIapHelper = IapHelper.getInstance( this.getApplicationContext() );
        mIapHelper.setOperationMode(IAP_MODE);

        mOwnedListAdapter = new OwnedListAdapter(this, mIapHelper);
        mPaymentAdapter = new PaymentAdapter(this, mIapHelper);
        mPaymentAdapter.setPassThroughParam("TEST_PASS_THROUGH");

        createImage();
        getOwnedList();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        setPreferences();
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        mIapHelper.dispose();
    }

    protected void createImage()
    {
        mTextBulletCnt       = (TextView) findViewById( R.id.textBulletCnt );
        mTextMaxBullet       = (TextView) findViewById( R.id.textMaxBullet );
        mTextInfiniteBullet = (TextView) findViewById( R.id.textInfiniteBullet );
        showBulletCount(mBulletCnt);

        mGunImage1 = (ImageView) findViewById( R.id.imageGun01);
        mGunImage2 = (ImageView) findViewById( R.id.imageGun02);
        showGunImage(mGunLevel);

        mShotImage = (ImageView) findViewById( R.id.imageViewShot);
    }

    public void onClick( View _view )
    {
        if( null == _view )
        {
            return;
        }
        switch( _view.getId() ) {
            case R.id.btn_shot:
            {
                if(mBulletCnt==0 && mInfiniteBullet == false)
                    showToastMessage("You are out of bullets! Try get some!");
                else {
                    minusBullet();
                    TransitionDrawable drawable = (TransitionDrawable) mShotImage.getDrawable();
                    drawable.startTransition(500);
                    drawable.reverseTransition(500);
                }
                break;
            }
            case R.id.btn_get_a_bullet:
            {
                if(mBulletCnt>=5 || mInfiniteBullet == true)
                    showToastMessage("You already have max bullets!");
                else
                    purchaseProduct(ItemDefine.ITEM_ID_CONSUMABLE);
                break;
            }
            case R.id.btn_upgrade_the_gun:
            {
                purchaseProduct(ItemDefine.ITEM_ID_NONCONSUMABLE);
                break;
            }
            case R.id.btn_get_infinite_bullets:
            {
                purchaseProduct(ItemDefine.ITEM_ID_SUBSCRIPTION);
                break;
            }
            case R.id.buttonProductsDetails: {
                Intent intent = new Intent(MainActivity.this,
                        ProductsDetailsActivity.class);
                intent.putExtra("ProductIds", "consumable,non-consumable,ARS");
                startActivity(intent);

                break;
            }
            default:
                break;
        }
    }

    protected void purchaseProduct(String itemId) {
        if(mPaymentAdapter != null) {
            mIapHelper.startPayment(itemId,
                    mPaymentAdapter.getPassThroughParam(),
                    true,
                    mPaymentAdapter);
        }
    }

    protected void getOwnedList() {
        Log.v(TAG, "getOwnedList");
        if(mOwnedListAdapter != null) {
            mIapHelper.getOwnedList(IapHelper.PRODUCT_TYPE_ALL,
                    mOwnedListAdapter);
        }
    }

    public  void  plusBullet()
    {
        if (mBulletCnt < 5) {
            mBulletCnt++;
            showBulletCount(mBulletCnt);
        }
    }

    public  void  minusBullet()
    {
        if(mInfiniteBullet == false) {
            if (mBulletCnt > 0) {
                mBulletCnt--;
                showBulletCount(mBulletCnt);
            }
        }
    }

    public void setGunLevel(int _gunLevel)
    {
        mGunLevel = _gunLevel;
        showGunImage(mGunLevel);
    }

    public void setInfiniteBullet(boolean _infiniteBullet)
    {
        mInfiniteBullet = _infiniteBullet;
        showBulletCount(mBulletCnt);
    }

    protected void setPreferences()
    {
            SharedPreferences sharedPreferences = getSharedPreferences(ShardPrefConstants.FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(ShardPrefConstants.KEY_BULLET_COUNT, mBulletCnt);
            editor.putInt(ShardPrefConstants.KEY_GUN_LEVEL, mGunLevel);
            editor.putBoolean(ShardPrefConstants.KEY_INFINITE_BULLET, mInfiniteBullet);
            editor.apply();
    }

    protected void getPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(ShardPrefConstants.FILE_NAME, Context.MODE_PRIVATE);
        mBulletCnt = sharedPreferences.getInt(ShardPrefConstants.KEY_BULLET_COUNT, 5);
        mGunLevel = sharedPreferences.getInt(ShardPrefConstants.KEY_GUN_LEVEL, 0);
        mInfiniteBullet = sharedPreferences.getBoolean(ShardPrefConstants.KEY_INFINITE_BULLET, false);
    }

    protected void showBulletCount(int count)
    {
        String bulletCnt = "";
        if(mInfiniteBullet == true)
        {
            mTextBulletCnt.setVisibility(View.INVISIBLE);
            mTextMaxBullet.setVisibility(View.INVISIBLE);
            mTextInfiniteBullet.setVisibility(View.VISIBLE);
        }
        else if(count>=0 && count <= 5) {
            bulletCnt = "" + count;
            mTextBulletCnt.setVisibility(View.VISIBLE);
            mTextMaxBullet.setVisibility(View.VISIBLE);
            mTextInfiniteBullet.setVisibility(View.INVISIBLE);
            mTextBulletCnt.setText(bulletCnt);
        }
    }

    protected void showGunImage(int level)
    {
        if (level == 0) {
            mGunImage1.setVisibility(View.VISIBLE);
            mGunImage2.setVisibility(View.INVISIBLE);
        }
        else
        {
            mGunImage1.setVisibility(View.INVISIBLE);
            mGunImage2.setVisibility(View.VISIBLE);
        }
    }

    protected void showToastMessage(String message)
    {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this.getApplicationContext(), message, duration);
        toast.show();
    }
}

