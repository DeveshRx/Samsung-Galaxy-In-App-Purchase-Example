package com.samsung.android.sdk.iap.v6.sample1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.android.sdk.iap.lib.helper.IapHelper;
import com.samsung.android.sdk.iap.lib.listener.OnGetProductsDetailsListener;
import com.samsung.android.sdk.iap.lib.vo.ErrorVo;
import com.samsung.android.sdk.iap.lib.vo.ProductVo;
import  com.samsung.android.sdk.iap.v6.sample1.adapter.ProductsDetailsAdapter;
import com.samsung.android.sdk.iap.v6.sample1.R;

import java.util.ArrayList;

public class ProductsDetailsActivity extends Activity
                                implements OnGetProductsDetailsListener
{
    private final String TAG = ProductsDetailsActivity.class.getSimpleName();
    
    private String    mProductIds    = "";

    private int       mIapMode     = 0;    private ListView  mProductListView      = null;
    private TextView  mNoDataTextView    = null;
    //private TextView mSelectedProductType  = null;
    
    /** ArrayList for product */
    private ArrayList<ProductVo> mProductList      = new ArrayList<ProductVo>();
    
    /** AdapterView for ProductList */
    private ProductsDetailsAdapter mProductAdapter   = null;
    private IapHelper mIapHelper = null;
    
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        setContentView( R.layout.product_details_layout );

        //    passed by Intent
        // ====================================================================
        Intent intent = getIntent();
        
        if( intent != null && intent.getExtras() != null 
                && intent.getExtras().containsKey( "ProductIds" ))
        {
            Bundle extras = intent.getExtras();
            mProductIds    = extras.getString( "ProductIds" );
        }
        else
        {
            Toast.makeText( this, 
                            R.string.mids_sapps_pop_an_invalid_value_has_been_provided_for_samsung_in_app_purchase,
                            Toast.LENGTH_LONG ).show();
            finish();
        }
        // ====================================================================
        
        ///////////////////////////////////////////////////////////////////////
        mIapHelper = IapHelper.getInstance(this);
        if(mIapHelper != null)
        {
            mIapHelper.getProductsDetails( mProductIds,
                    this );

            initView();
        }
        else {
            finish();
        }
        ///////////////////////////////////////////////////////////////////////
    }

    
    /**
     * initialize views
     */
    public void initView()
    {
        // 1. set views of Product Details
        // ====================================================================
        mProductListView   = (ListView)findViewById( R.id.productList );
        mNoDataTextView = (TextView)findViewById( R.id.noDataText );
/*
        mSelectedProductType = (TextView)findViewById(
                                                 R.id.txt_selected_product_type );
        mSelectedProductType.setText( mProductType );
*/
        mProductAdapter = new ProductsDetailsAdapter( this,
                                                R.layout.product_row,
                                                mProductList );
        
        mProductListView.setAdapter( mProductAdapter );
        mProductListView.setEmptyView( mNoDataTextView );
        mProductListView.setVisibility( View.GONE );

    }

    @Override
    public void onGetProducts(ErrorVo _errorVo, ArrayList<ProductVo> _ProductList )
    {
        Log.v(TAG,"onGetProducts");
        Log.v(TAG,"_errorVo.getErrorCode() : " + _errorVo.getErrorCode());
        if( _errorVo != null &&
            _errorVo.getErrorCode() == IapHelper.IAP_ERROR_NONE )
        {
            Log.v(TAG,"onGetProducts");
            if( _ProductList != null && _ProductList.size() > 0 )
            {
                mProductList.addAll( _ProductList );
                mProductAdapter.notifyDataSetChanged();                
            }
        }
    }
}
