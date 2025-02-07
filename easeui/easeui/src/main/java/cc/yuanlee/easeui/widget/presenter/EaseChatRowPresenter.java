package cc.yuanlee.easeui.widget.presenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.R;
import cc.yuanlee.easeui.model.styles.EaseMessageListItemStyle;
import cc.yuanlee.easeui.widget.EaseAlertDialog;
import cc.yuanlee.easeui.widget.EaseChatMessageList;
import cc.yuanlee.easeui.widget.chatrow.EaseChatRow;

/**
 * Created by zhangsong on 17-10-12.
 */

public abstract class EaseChatRowPresenter implements EaseChatRow.EaseChatRowActionCallback {
    private EaseChatRow chatRow;

    private Context context;
    private BaseAdapter adapter;
    private EMMessage message;
    private int position;

    @Override
    public void onResendClick(final EMMessage message) {
        new EaseAlertDialog(getContext(), R.string.resend, R.string.confirm_resend, null, new EaseAlertDialog.AlertDialogUser() {
            @Override
            public void onResult(boolean confirmed, Bundle bundle) {
                if (!confirmed) {
                    return;
                }
                message.setStatus(EMMessage.Status.CREATE);
                handleSendMessage(message);
            }
        }, true).show();
    }

    @Override
    public void onBubbleClick(EMMessage message) {
    }

    @Override
    public void onDetachedFromWindow() {
    }

    public EaseChatRow createChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        this.context = cxt;
        this.adapter = adapter;
        chatRow = onCreateChatRow(cxt, message, position, adapter);
        return chatRow;
    }

    public void setup(EMMessage msg, int position,
                      EaseChatMessageList.MessageListItemClickListener itemClickListener,
                      EaseMessageListItemStyle itemStyle) {
        this.message = msg;
        this.position = position;

        chatRow.setUpView(message, position, itemClickListener, this, itemStyle);

        handleMessage();
    }

    protected void handleSendMessage(final EMMessage message) {
        // Update the view according to the message current status.
        getChatRow().updateView(message);
    }

    protected void handleReceiveMessage(EMMessage message) {
    }

    protected abstract EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter);

    protected EaseChatRow getChatRow() {
        return chatRow;
    }

    protected Context getContext() {
        return context;
    }

    protected BaseAdapter getAdapter() {
        return adapter;
    }

    protected EMMessage getMessage() {
        return message;
    }

    protected int getPosition() {
        return position;
    }

    private void handleMessage() {
        if (message.direct() == EMMessage.Direct.SEND) {
            handleSendMessage(message);
        } else if (message.direct() == EMMessage.Direct.RECEIVE) {
            handleReceiveMessage(message);
        }
    }
}
