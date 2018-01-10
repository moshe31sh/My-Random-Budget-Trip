package moshe.com.my_random_budget_trip.view.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager

/**
 * Created by moshe on 10/01/2018.
 */
abstract class BaseFullScreenDialog(private val mContext: Context) : Dialog(mContext){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        setContentView(getContentView())
        init()
    }

    abstract protected fun init()

    abstract protected fun showDialog()

    abstract protected fun getContentView() : Int

}