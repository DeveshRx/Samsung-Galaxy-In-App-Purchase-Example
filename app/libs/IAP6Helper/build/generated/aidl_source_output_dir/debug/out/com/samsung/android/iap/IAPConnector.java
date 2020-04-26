/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.samsung.android.iap;
public interface IAPConnector extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.samsung.android.iap.IAPConnector
{
private static final java.lang.String DESCRIPTOR = "com.samsung.android.iap.IAPConnector";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.samsung.android.iap.IAPConnector interface,
 * generating a proxy if needed.
 */
public static com.samsung.android.iap.IAPConnector asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.samsung.android.iap.IAPConnector))) {
return ((com.samsung.android.iap.IAPConnector)iin);
}
return new com.samsung.android.iap.IAPConnector.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
java.lang.String descriptor = DESCRIPTOR;
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(descriptor);
return true;
}
case TRANSACTION_requestCmd:
{
data.enforceInterface(descriptor);
com.samsung.android.iap.IAPServiceCallback _arg0;
_arg0 = com.samsung.android.iap.IAPServiceCallback.Stub.asInterface(data.readStrongBinder());
android.os.Bundle _arg1;
if ((0!=data.readInt())) {
_arg1 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
boolean _result = this.requestCmd(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unregisterCallback:
{
data.enforceInterface(descriptor);
com.samsung.android.iap.IAPServiceCallback _arg0;
_arg0 = com.samsung.android.iap.IAPServiceCallback.Stub.asInterface(data.readStrongBinder());
boolean _result = this.unregisterCallback(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getProductsDetails:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
android.os.Bundle _result = this.getProductsDetails(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getOwnedList:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
android.os.Bundle _result = this.getOwnedList(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_consumePurchasedItems:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
android.os.Bundle _result = this.consumePurchasedItems(_arg0, _arg1, _arg2);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_requestServiceAPI:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
android.os.Bundle _result = this.requestServiceAPI(_arg0, _arg1, _arg2);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.samsung.android.iap.IAPConnector
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public boolean requestCmd(com.samsung.android.iap.IAPServiceCallback callback, android.os.Bundle bundle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
if ((bundle!=null)) {
_data.writeInt(1);
bundle.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_requestCmd, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean unregisterCallback(com.samsung.android.iap.IAPServiceCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
///////////////////////////// IAP 5.0

@Override public android.os.Bundle getProductsDetails(java.lang.String packageName, java.lang.String itemIds, int pagingIndex, int mode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(itemIds);
_data.writeInt(pagingIndex);
_data.writeInt(mode);
mRemote.transact(Stub.TRANSACTION_getProductsDetails, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.os.Bundle.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.os.Bundle getOwnedList(java.lang.String packageName, java.lang.String itemType, int pagingIndex, int mode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(itemType);
_data.writeInt(pagingIndex);
_data.writeInt(mode);
mRemote.transact(Stub.TRANSACTION_getOwnedList, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.os.Bundle.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.os.Bundle consumePurchasedItems(java.lang.String packageName, java.lang.String purchaseIds, int mode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(purchaseIds);
_data.writeInt(mode);
mRemote.transact(Stub.TRANSACTION_consumePurchasedItems, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.os.Bundle.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.os.Bundle requestServiceAPI(java.lang.String packageName, java.lang.String requestId, java.lang.String extraData) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(requestId);
_data.writeString(extraData);
mRemote.transact(Stub.TRANSACTION_requestServiceAPI, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.os.Bundle.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_requestCmd = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getProductsDetails = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getOwnedList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_consumePurchasedItems = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_requestServiceAPI = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
public boolean requestCmd(com.samsung.android.iap.IAPServiceCallback callback, android.os.Bundle bundle) throws android.os.RemoteException;
public boolean unregisterCallback(com.samsung.android.iap.IAPServiceCallback callback) throws android.os.RemoteException;
///////////////////////////// IAP 5.0

public android.os.Bundle getProductsDetails(java.lang.String packageName, java.lang.String itemIds, int pagingIndex, int mode) throws android.os.RemoteException;
public android.os.Bundle getOwnedList(java.lang.String packageName, java.lang.String itemType, int pagingIndex, int mode) throws android.os.RemoteException;
public android.os.Bundle consumePurchasedItems(java.lang.String packageName, java.lang.String purchaseIds, int mode) throws android.os.RemoteException;
public android.os.Bundle requestServiceAPI(java.lang.String packageName, java.lang.String requestId, java.lang.String extraData) throws android.os.RemoteException;
}
