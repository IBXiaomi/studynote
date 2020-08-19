package com.baxixiaomi.code.demo.rpc.register;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 注册中心
 *
 * @author 吧嘻小米
 * @date 2020/06/28
 */
public class RpcRegister {

    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host, port);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeUTF(method.getName());
                objectOutputStream.writeObject(method.getParameterTypes());
                objectOutputStream.writeObject(args);

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Object object = objectInputStream.readObject();
                return object;
            }
        });
    }

    /**
     * 暴露服务(反射)
     *
     * @param object
     * @param port
     */
    public static void export(final Object object, int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            for (; ; ) {
                final Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                            String methodName = objectInputStream.readUTF();
                            Class<?>[] paramsTypes = (Class<?>[]) objectInputStream.readObject();
                            Object[] arguments = (Object[]) objectInputStream.readObject();

                            Method method = object.getClass().getMethod(methodName, paramsTypes);
                            Object invoke = method.invoke(object, arguments);

                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                            objectOutputStream.writeObject(invoke);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
