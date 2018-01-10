package moshe.com.my_random_budget_trip.view.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import moshe.com.my_random_budget_trip.R

/**
 * Created by moshe on 10/01/2018.
 */
class LoadingDialog(private var  mContext: Context) : BaseFullScreenDialog(mContext) {

    override fun init() {
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
    }


    override fun showDialog() {
        super.show()
    }

    override fun getContentView(): Int = R.layout.custom_dialog_progress

}