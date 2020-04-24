package  com.samsung.android.sdk.iap.v6.sample1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.samsung.android.sdk.iap.lib.vo.ProductVo;
import com.samsung.android.sdk.iap.v6.sample1.R;

import java.util.ArrayList;

public class ProductsDetailsAdapter extends ArrayAdapter<ProductVo>
{
    private int               mResId    = 0;

    private LayoutInflater    mInflater = null;
    private ArrayList<ProductVo> mItems    = null;

    public ProductsDetailsAdapter
    (
            Context             _context,
            int                 _resId,
            ArrayList<ProductVo>   _items
    )
    {
        super( _context, _resId, _items );

        mResId    = _resId;
        mItems    = _items;
        mInflater = (LayoutInflater)_context.getSystemService( 
                                             Context.LAYOUT_INFLATER_SERVICE );
    }

    
    public static class ViewHolder
    {
        TextView productName;
        TextView productPriceString;
        TextView productType;
        TextView productDescription;
    }
    

    @Override
    public View getView
    (   
        final int       _position,
        View            _convertView,
        final ViewGroup _parent
    )
    {
        final ProductVo vo = mItems.get( _position );
        ViewHolder vh;
        View v = _convertView;

        if( v == null )
        {
            vh = new ViewHolder();
            
            v = mInflater.inflate( mResId, null );
            
            vh.productName        = (TextView)v.findViewById( R.id.productName );
            
            vh.productPriceString = (TextView)v.findViewById(
                                                        R.id.productPriceString );
            
            vh.productType        = (TextView)v.findViewById( R.id.productType );
            
            vh.productDescription = (TextView)v.findViewById(
                                                        R.id.productDescription );
            v.setTag( vh );
        }
        else
        {
            vh = (ViewHolder)v.getTag();
        }

        vh.productName.setText( vo.getItemName() );
        vh.productPriceString.setText( vo.getItemPriceString() );
        
        String productType = "Type : ";
        
        if( true == "item".equals( vo.getType() ) )
        {
            productType += "item";
        }
        else if( true == "subscription".equals( vo.getType() ) )
        {
            productType += "subscription";
        }
        else
        {
            productType += "Unsupported type";
        }
        
        vh.productType.setText( productType );
        
        vh.productDescription.setText( vo.getItemDesc() );

        return v;
    }
}