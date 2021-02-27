package com.zerox;

import org.apache.commons.io.IOUtils;
import org.tensorflow.Graph;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2021/2/27 9:46
 * @Description:
 * @Modified By: zhuxi
 */
public class TensorFlowTest {
    /**
     * 这种格式是给没有变量文件夹的模型加载调用的
     *
     * @throws Exception
     */
    public static void predict() throws Exception {
        try (Graph graph = new Graph()) {
            byte[] graphBytes = IOUtils.toByteArray(new FileInputStream("tensorflowTest\\src\\main\\resources\\lr_model_save_path"));
            graph.importGraphDef(graphBytes);

            /// 报错：java.lang.IllegalArgumentException: Invalid GraphDef
//            graph.importGraphDef(Files.readAllBytes(Paths.get(
//                    "tensorflowTest\\src\\main\\resources"
//            )));
            try (Session sess = new Session(graph)) {
                // 自己构造一个输入
                float[][] input = {{0.000000f, -1.359807f, -0.072781f, 2.536347f, 1.378155f,
                        -0.338321f, 0.462388f, 0.239599f, 0.098698f, 0.363787f,
                        0.090794f, -0.551600f, -0.617801f, -0.991390f, -0.311169f,
                        1.468177f, -0.470401f, 0.207971f, 0.025791f, 0.403993f,
                        0.251412f, -0.018307f, 0.277838f, -0.110474f, 0.066928f,
                        0.128539f, -0.189115f, 0.133558f, -0.021053f, 149.620000f}};
                try (Tensor x = Tensor.create(input);
                     // input是输入的name，output是输出的name
                     Tensor y = sess.runner().feed("input", x).fetch("output").run().get(0)) {
                    System.out.println(Arrays.toString(y.shape()));
                    float[][] result = new float[1][(int) y.shape()[1]];
                    y.copyTo(result);
                    System.out.println(Arrays.toString(result[0]));
                }
            }
        }
    }

    public static void predict2() throws Exception {
        // 判断相对路径的开始位置
        File file = new File("test");
        System.out.println(file.getAbsolutePath());

        SavedModelBundle smb = SavedModelBundle.load("tensorflowTest\\src\\main\\resources\\lr_model_save_path", "serve");
        try (Session sess = smb.session()) {
            // 自己构造一个输入，必须是float
            float[][] input = {{0.000000f, -1.359807f, -0.072781f, 2.536347f, 1.378155f,
                    -0.338321f, 0.462388f, 0.239599f, 0.098698f, 0.363787f,
                    0.090794f, -0.551600f, -0.617801f, -0.991390f, -0.311169f,
                    1.468177f, -0.470401f, 0.207971f, 0.025791f, 0.403993f,
                    0.251412f, -0.018307f, 0.277838f, -0.110474f, 0.066928f,
                    0.128539f, -0.189115f, 0.133558f, -0.021053f, 149.620000f}};
            try (Tensor x = Tensor.create(input);
                 // serving_default_dense_2_input:0 是输入的name，StatefulPartitionedCall:0 是输出的name
                 // 使用saved_model_cli show --dir model_dir_path --all 可以查看，model_dir_path为模型保存路径，例如./lr_model_save_path
                 // 结果示例：
                 // -----------------------------------------------------
                 // signature_def['serving_default']:
                 //  The given SavedModel SignatureDef contains the following input(s):
                 //    inputs['dense_2_input'] tensor_info:
                 //        dtype: DT_FLOAT
                 //        shape: (-1, 30)
                 //        name: serving_default_dense_2_input:0
                 //  The given SavedModel SignatureDef contains the following output(s):
                 //    outputs['dense_3'] tensor_info:
                 //        dtype: DT_FLOAT
                 //        shape: (-1, 1)
                 //        name: StatefulPartitionedCall:0
                 //  Method name is: tensorflow/serving/predict
                 // -----------------------------------------------------
                 Tensor y = sess.runner().feed("serving_default_dense_2_input:0", x).fetch("StatefulPartitionedCall:0").run().get(0)) {
                System.out.println(Arrays.toString(y.shape()));
                float[][] result = new float[1][(int) y.shape()[1]];
                y.copyTo(result);
                System.out.println(Arrays.toString(result[0]));
            }
        }
    }

    public static void main(String[] args) {
        try {
            predict2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
