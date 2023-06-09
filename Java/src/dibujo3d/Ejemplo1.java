/*
 * Ejemplo1.java
 *
 * Created on 29 de septiembre de 2008, 09:30 AM
 */
package dibujo3d;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
//import gnu.io.SerialPortEvent;
//import gnu.io.SerialPortEventListener;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.PI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;
//import panamahitek.Arduino.PanamaHitek_Arduino;

/**
 *
 * @author  rvirtual
 */
public class Ejemplo1 extends javax.swing.JApplet 
{

    TextureLoader myLoader = new TextureLoader("FondoLadrillos.jpg", this);
    ImageComponent2D myImage = myLoader.getImage();
    
    private SimpleUniverse simpleU=null;
    private TransformGroup obj0, obj1,  obj2, obj3;
    float luc=0.0f;
    int cont=0;
    int cont1=0;
    int cont2=0;
    int contt=0;
    double cont3=0;
    double cont4=0;
    Timer tiempo;
    int contador=0;
 
//    private TransformGroup camara;
    
   
  /*  PanamaHitek_Arduino Arduino= new PanamaHitek_Arduino();
    SerialPortEventListener evento= new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
        }
    };

    /** Initializes the applet Ejemplo1 */
    public void init() {
           tiempo= new Timer(600, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                funcion();
            }
        });
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    
                    initComponents();
                    try {
//            Arduino.arduinoRXTX("COM7", 9600, evento);
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
                    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
                    Canvas3D canvas3D = new Canvas3D(config);

                    jPanel2.add("Center", canvas3D);

                    BranchGroup scene = createSceneGraph();
                    scene.compile();

                    simpleU = new SimpleUniverse(canvas3D);
                    simpleU.getViewingPlatform().setNominalViewingTransform();
                    simpleU.addBranchGraph(scene);
                    
                    posicion();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
        
        //Inicializar objetos
        obj0 = new TransformGroup();
        obj0.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        obj1 = new TransformGroup();
        obj1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        obj2 = new TransformGroup();
        obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        obj3 = new TransformGroup();
        obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
      
        //--------------------------------------------------------------------//
        
        obj0.addChild(loadGeometryWRL("ensamblajebase.wrl"));
        objRoot.addChild(obj0);

        obj1.addChild(loadGeometryWRL("ensamblajebasegiratoria.wrl"));
        objRoot.addChild(obj1);
        
        obj2.addChild(loadGeometryWRL("ensamblajeantebrazo.wrl"));
        obj1.addChild(obj2);
        
        obj3.addChild(loadGeometryWRL("ensamblajebrazo.wrl"));
        
//         obj0.addChild(loadGeometryWRL("ensamblajebase.wrl"));
//        objRoot.addChild(obj0);
//
//        obj1.addChild(loadGeometryWRL("ensamblajebasegiratoria.wrl"));
//        objRoot.addChild(obj1);
//        
//        obj2.addChild(loadGeometryWRL("ensamblajeantebrazo.wrl"));
//        obj1.addChild(obj2);
//        
//        obj3.addChild(loadGeometryWRL("ensamblajebrazo.wrl"));
//        
        //--------------------------------------------------------------------//
        
        Transform3D local = new Transform3D();
        local.setTranslation(new Vector3d(0.0792,0,0));
        obj3.setTransform(local);
        
        Transform3D local1 = new Transform3D();
        local.setTranslation(new Vector3d(0.015,0,0));
        obj0.setTransform(local);
        
        Transform3D actual = new Transform3D();
        obj3.getTransform(actual);
        
        Transform3D rotacion = new Transform3D();
        rotacion.rotZ(-Math.PI/4);
        actual.mul(rotacion);
        obj3.setTransform(actual);
        obj2.addChild(obj3);
        
        //--------------------------------------------------------------------//
        
        //luces
        objRoot.addChild(luces());

        //fondo
        objRoot.addChild(fondo());

        //piso
        objRoot.addChild(piso());
        
        return objRoot;
    }

    public BranchGroup loadGeometryWRL(String geometryURL) {
        BranchGroup objLoad = new BranchGroup();

        VrmlLoader wrl = new VrmlLoader();
        try {
            objLoad = wrl.load(geometryURL).getSceneGroup();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ParsingErrorException ex) {
            ex.printStackTrace();
        } catch (IncorrectFormatException ex) {
            ex.printStackTrace();
        }
        return objLoad;
    }

    private TransformGroup fondo() {
        TransformGroup objRoot = new TransformGroup();
        Background font = new Background(myImage);
        //Background font = new Background(new Color3f(0.1f, 0.1f, 0.1f)); //0.5f 0.6f 0.9f
        //Color3f(1.0f, 0.6f, 0.9f)
        font.setApplicationBounds(new BoundingSphere(new Point3d(), 100.0));
        objRoot.addChild(font);
        return objRoot;
    }

    private TransformGroup luces() {
        TransformGroup objRoot = new TransformGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 80), 100.0);
        Color3f lightColor = new Color3f(0.6f, 0.6f, 0.6f);
        Vector3f light1Direction = new Vector3f(0.5f, 1.0f, -1.0f);
        DirectionalLight luz1 = new DirectionalLight(lightColor, light1Direction);
        luz1.setInfluencingBounds(bounds);
        objRoot.addChild(luz1);
        Transform3D actual = new Transform3D();
        objRoot.getTransform(actual);
        AmbientLight luz2 = new AmbientLight(lightColor);
        Transform3D nuevo = new Transform3D();
        nuevo.set(new Vector3d(0,0,0.1));
        luz2.setInfluencingBounds(bounds);
        objRoot.addChild(luz2);
        objRoot.setTransform(nuevo);

        return objRoot;
    }

    private TransformGroup piso() {
        TransformGroup sueloTransf = new TransformGroup();

        int tamano = 100;
        Point3f[] vertices = new Point3f[tamano * tamano];

        float inicio = -20.0f;
        float x = inicio;
        float z = inicio;

        float salto = 1.0f;

        int[] indices = new int[(tamano - 1) * (tamano - 1) * 4];
        int n = 0;

        Color3f blanco = new Color3f(0.15f, 0.15f, 0.15f);
        Color3f negro = new Color3f(0.2f, 0.2f, 0.2f);
        Color3f[] colors = {blanco, negro};

        int[] colorindices = new int[indices.length];

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                vertices[i * tamano + j] = new Point3f(x, -0.079f, z);
                z += salto;
                if (i < (tamano - 1) && j < (tamano - 1)) {
                    int cindex = (i % 2 + j) % 2;
                    colorindices[n] = cindex;
                    indices[n++] = i * tamano + j;
                    colorindices[n] = cindex;
                    indices[n++] = i * tamano +
                            (j + 1);
                    colorindices[n] = cindex;
                    indices[n++] = (i + 1) *
                            tamano + (j + 1);
                    colorindices[n] = cindex;
                    indices[n++] = (i + 1) *
                            tamano + j;
                }
            }
            z = inicio;
            x += salto;
        }

        IndexedQuadArray geom = new IndexedQuadArray(vertices.length,
                GeometryArray.COORDINATES |
                GeometryArray.COLOR_3,
                indices.length);
        geom.setCoordinates(0, vertices);
        geom.setCoordinateIndices(0, indices);
        geom.setColors(0, colors);
        geom.setColorIndices(0, colorindices);

        Shape3D suelo = new Shape3D(geom);
        sueloTransf.addChild(suelo);

        return sueloTransf;
    }

    private void posicion(){// funcion para posicionar correctamente la camara 
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
  
        Transform3D vista = new Transform3D();
        vista.set(new Vector3d(0, 0.1,0.8));
        universo.setTransform(vista); 
    }
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        RotYPos = new javax.swing.JButton();
        RotYNeg = new javax.swing.JToggleButton();
        RotXPos = new javax.swing.JButton();
        RotXNeg = new javax.swing.JButton();
        RotZPos = new javax.swing.JButton();
        RotZNeg = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TrasYNeg = new javax.swing.JButton();
        TrasYPos = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TrasXNeg = new javax.swing.JButton();
        TrasXPos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        TrasZNeg = new javax.swing.JButton();
        TrasZPos = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        jButton9.setText("jButton9");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(219, 184, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MANEJO DEL BRAZO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setText("+Mov Brazo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("-Mov Brazo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("+Mov Base");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("-Mov Base");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("+Mov Antebrazo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("-Mov Antebrazo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(219, 184, 173));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CÁMARA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setText("+");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, -1, -1));

        jButton8.setText("-");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 16, -1, -1));

        jPanel6.setBackground(new java.awt.Color(245, 238, 238));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setForeground(new java.awt.Color(222, 206, 223));

        RotYPos.setText("+");
        RotYPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RotYPosActionPerformed(evt);
            }
        });

        RotYNeg.setText("-");
        RotYNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RotYNegActionPerformed(evt);
            }
        });

        RotXPos.setText("+");
        RotXPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RotXPosActionPerformed(evt);
            }
        });

        RotXNeg.setText("-");
        RotXNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RotXNegActionPerformed(evt);
            }
        });

        RotZPos.setText("+");
        RotZPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RotZPosActionPerformed(evt);
            }
        });

        RotZNeg.setText("-");
        RotZNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RotZNegActionPerformed(evt);
            }
        });

        jLabel1.setText("Rotación en Z:");

        jLabel2.setText("Rotación en X:");

        jLabel3.setText("Rotación en Y:");

        jLabel8.setText("Traslación en Y:");

        TrasYNeg.setText("-");
        TrasYNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrasYNegActionPerformed(evt);
            }
        });

        TrasYPos.setText("+");
        TrasYPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrasYPosActionPerformed(evt);
            }
        });

        jLabel7.setText("Traslación en X:");

        TrasXNeg.setText("-");
        TrasXNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrasXNegActionPerformed(evt);
            }
        });

        TrasXPos.setText("+");
        TrasXPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrasXPosActionPerformed(evt);
            }
        });

        jLabel6.setText("Traslación en Z:");

        TrasZNeg.setText("-");
        TrasZNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrasZNegActionPerformed(evt);
            }
        });

        TrasZPos.setText("+");
        TrasZPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrasZPosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(RotYNeg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RotYPos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TrasZNeg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TrasZPos))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TrasXNeg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TrasXPos))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(RotZNeg)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(RotZPos))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(RotXNeg)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(RotXPos))
                                    .addComponent(jLabel3))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(TrasYNeg)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TrasYPos))
                                    .addComponent(jLabel8))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(34, 34, 34))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RotZNeg)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RotZPos)
                        .addComponent(TrasYPos)
                        .addComponent(TrasYNeg)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RotXNeg)
                    .addComponent(RotXPos)
                    .addComponent(TrasXNeg)
                    .addComponent(TrasXPos))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RotYNeg)
                    .addComponent(RotYPos)
                    .addComponent(TrasZPos)
                    .addComponent(TrasZNeg))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 280, 230));

        jPanel7.setBackground(new java.awt.Color(245, 238, 238));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton15.setText("Isometrica");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Superior");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Frontal");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel5.setText("Vistas:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Simulación1", "Simulación2" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Trayectorias:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jButton15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(101, 101, 101))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(123, 123, 123))))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addGap(18, 18, 18)
                .addComponent(jButton17)
                .addGap(28, 28, 28))
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 280, 220));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Movimiento");

        jPanel2.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel2MouseWheelMoved(evt);
            }
        });
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
public void funcion()
{
    jTextField1.setText(String.valueOf(contador));
contador++;
String giro;
if(contador==1){
Transform3D local = new Transform3D();
      local.setTranslation(new Vector3d(0.08,0,0));
        obj3.setTransform(local);
        Transform3D actual = new Transform3D();
        obj3.getTransform(actual);
        Transform3D rotacion = new Transform3D();
        rotacion.rotZ(-Math.PI/4);
        actual.mul(rotacion);
        obj3.setTransform(actual);
        try {
//            Arduino.sendData("i"+String.valueOf(12*180/16));
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
}
 
if(contador>1 && contador<12)
{   contt=0;
    contt++;
   Transform3D actual1 = new Transform3D();
    obj1.getTransform(actual1);
    Transform3D inc = new Transform3D();
    inc.rotY((contt*Math.PI) / 16);
    actual1.mul(inc);
    obj1.setTransform(actual1); 

}
else
{
    if(contador>12 && contador<20 )
    {
        
        contt=0;
        contt++;
    cont4=cont4+Math.PI/16;
    giro=String.valueOf(cont4*180/Math.PI);
    Transform3D actual1 = new Transform3D();
    obj2.getTransform(actual1);
    Transform3D inc = new Transform3D();
    inc.rotZ((contt*Math.PI) / 16);
    actual1.mul(inc);
    try {
//            Arduino.sendData("y"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    obj2.setTransform(actual1);
    
    }
if(contador>20 && contador<28 )
    {
        contt=0;
        contt++;
        cont3=cont3+Math.PI/16;
    giro=String.valueOf(cont3*180/Math.PI);
       Transform3D actual1 = new Transform3D();
    obj3.getTransform(actual1);
    Transform3D inc = new Transform3D();
    inc.rotZ(-(contt*Math.PI) / 16);
    actual1.mul(inc);
    try {
//            Arduino.sendData("u"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    obj3.setTransform(actual1);
    }
if(contador==28){
    try {
//            Arduino.sendData("i-"+String.valueOf(12*180/16));
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
}
if(contador>28 && contador <40)
{
    contt=0;
    contt++;
   Transform3D actual1 = new Transform3D();
    obj1.getTransform(actual1);
    Transform3D inc = new Transform3D();
    inc.rotY(-(contt*Math.PI) / 16);
    actual1.mul(inc);
    obj1.setTransform(actual1); 

}
if(contador>40 && contador<48)
{  
    contt=0;
        contt++;
        cont4=cont4-Math.PI/16;
    giro=String.valueOf(cont4*180/Math.PI);
    Transform3D actual1 = new Transform3D();
    obj2.getTransform(actual1);
    Transform3D inc = new Transform3D();
    inc.rotZ(-(contt*Math.PI) / 16);
    actual1.mul(inc);
        try {
//            Arduino.sendData("y"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    obj2.setTransform(actual1);
    
}
if(contador>48 && contador<56)
{
       contt=0;
        contt++;
        cont3=cont3-Math.PI/16;
    giro=String.valueOf(cont3*180/Math.PI);
     Transform3D actual1 = new Transform3D();
    obj3.getTransform(actual1);
    Transform3D inc = new Transform3D();
    inc.rotZ((contt*Math.PI) / 16);
    actual1.mul(inc);
    try {
//            Arduino.sendData("u"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    obj3.setTransform(actual1);

}
if(contador>56)
{
    jTextField1.setText(String.valueOf(cont1+"y"+cont));
    tiempo.stop();
    contt=0;
    contador=0;
    

}
}

}

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     cont3=cont3+(Math.PI/16);
     String giro=String.valueOf(cont3*180/Math.PI);
     try {
//            Arduino.sendData("u"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    Transform3D actual = new Transform3D();
    obj3.getTransform(actual);
    Transform3D inc = new Transform3D();
    inc.rotZ(Math.PI/16);
    actual.mul(inc);
    obj3.setTransform(actual);
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    cont3=cont3-(Math.PI/16);
     String giro=String.valueOf(cont3*180/Math.PI);
     try {
//            Arduino.sendData("u"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    Transform3D actual = new Transform3D();
    obj3.getTransform(actual);
    Transform3D inc = new Transform3D();
    inc.rotZ(-Math.PI / 16);
    actual.mul(inc);
    obj3.setTransform(actual);
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    double girar = Math.PI / 16;
    String giro = String.valueOf(girar*180/Math.PI);
    jTextField1.setText(giro);
    try {
//            Arduino.sendData("i"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    Transform3D actual = new Transform3D();
    obj1.getTransform(actual);
    Transform3D inc = new Transform3D();
    inc.rotY(Math.PI / 16);
    actual.mul(inc);
    obj1.setTransform(actual);
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    double girar = Math.PI / 16;
    String giro = String.valueOf(girar*180/Math.PI);
    jTextField1.setText(giro);
    try {
//            Arduino.sendData("i-"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    Transform3D actual = new Transform3D();
    obj1.getTransform(actual);
    Transform3D inc = new Transform3D();
    inc.rotY(-Math.PI / 16);
    actual.mul(inc);
    obj1.setTransform(actual);
}//GEN-LAST:event_jButton4ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    cont4=cont4+(Math.PI/16);
     String giro=String.valueOf(cont4*180/Math.PI);
     try {
//            Arduino.sendData("y"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    Transform3D actual = new Transform3D();
    obj2.getTransform(actual);
    Transform3D inc = new Transform3D();
    inc.rotZ(Math.PI / 16);
    actual.mul(inc);
    obj2.setTransform(actual);
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    cont4=cont4-(Math.PI/16);
     String giro=String.valueOf(cont4*180/Math.PI);
     try {
//            Arduino.sendData("y"+giro);
            // TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    Transform3D actual = new Transform3D();
    obj2.getTransform(actual);
    Transform3D inc = new Transform3D();
    inc.rotZ(-Math.PI / 16);
    actual.mul(inc);
    obj2.setTransform(actual);
}//GEN-LAST:event_jButton6ActionPerformed

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    
    //Obtener TransformGroup camara
    TransformGroup universo =
            simpleU.getViewingPlatform().getViewPlatformTransform();
    
    //Obtener posicion de la camara con un transform3d
    Transform3D actual = new Transform3D();
    universo.getTransform(actual);
    
    //Crear un incremento
    Transform3D inc = new Transform3D();
    inc.set(new Vector3d(0,0,0.7));
    //Multiplicar posicion actual por incremento
    actual.mul(inc);
    //Escribir resultado de la nueva posicion
    universo.setTransform(actual);
}//GEN-LAST:event_jButton7ActionPerformed

private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    //Obtener TransformGroup camara
    TransformGroup universo =
            simpleU.getViewingPlatform().getViewPlatformTransform();
    
    //Obtener posicion de la camara con un transform3d
    Transform3D actual = new Transform3D();
    universo.getTransform(actual);
    
    //Crear un incremento
    Transform3D inc = new Transform3D();
    inc.set(new Vector3d(0,0,-0.7));
    //Multiplicar posicion actual por incremento
    actual.mul(inc);
    //Escribir resultado de la nueva posicion
    universo.setTransform(actual);
}//GEN-LAST:event_jButton8ActionPerformed

    private void RotZNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RotZNegActionPerformed
      //luc=luc+0.5f;
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        
        Transform3D actual= new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotX(-Math.PI / 16);
        actual.mul(inc);
        universo.setTransform(actual);
        
        
    }//GEN-LAST:event_RotZNegActionPerformed

    private void RotZPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RotZPosActionPerformed
      TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        
        Transform3D actual= new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotX(Math.PI / 16);
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_RotZPosActionPerformed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_W)
//        {
//    Transform3D actual = new Transform3D();
//    obj3.getTransform(actual);
//    Transform3D inc = new Transform3D();
//    inc.rotZ(Math.PI / 16);
//    actual.mul(inc);
//    obj3.setTransform(actual);
//        }
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jPanel2MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel2MouseWheelMoved
          if(evt.getWheelRotation()>0)
        {
      TransformGroup universo =
      simpleU.getViewingPlatform().getViewPlatformTransform();
    
 
    Transform3D actual = new Transform3D();
    universo.getTransform(actual);
   

    Transform3D inc = new Transform3D();
    inc.set(new Vector3d(0,0,-0.05));
    actual.mul(inc);
    universo.setTransform(actual);
        
        }
        else
        {
    TransformGroup universo =
            simpleU.getViewingPlatform().getViewPlatformTransform();
    
  
    Transform3D actual = new Transform3D();
    universo.getTransform(actual);
    

    Transform3D inc = new Transform3D();
    inc.set(new Vector3d(0,0,0.05));
    actual.mul(inc);
    universo.setTransform(actual);
            
        
        
        }
    }//GEN-LAST:event_jPanel2MouseWheelMoved

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
      double x;
      double y;
       //if(evt.getModifiersEx()== MouseEvent.BUTTON1_MASK)
   //  {
         
      TransformGroup universo =
            simpleU.getViewingPlatform().getViewPlatformTransform();
    
  
    Transform3D actual = new Transform3D();
    universo.getTransform(actual);
     x=evt.getX();
     y=evt.getX();
     System.out.println(x);
    Transform3D inc = new Transform3D();
    inc.set(new Vector3d(x,y,0));
    actual.mul(inc);
    universo.setTransform(actual);
   //  }
    }//GEN-LAST:event_jPanel2MouseDragged

    private void RotYNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RotYNegActionPerformed
          TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        
        Transform3D actual= new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotY(Math.PI / 16);
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_RotYNegActionPerformed

    private void RotXNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RotXNegActionPerformed
   TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        
        Transform3D actual= new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotZ(Math.PI / 16);
        actual.mul(inc);
        universo.setTransform(actual);    }//GEN-LAST:event_RotXNegActionPerformed

    private void RotYPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RotYPosActionPerformed
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        
        Transform3D actual= new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotY(-Math.PI / 16);
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_RotYPosActionPerformed

    private void RotXPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RotXPosActionPerformed
    TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        
        Transform3D actual= new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.rotZ(-Math.PI / 16);
        actual.mul(inc);
        universo.setTransform(actual);    }//GEN-LAST:event_RotXPosActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int a;
        a=jComboBox1.getSelectedIndex();
        switch(a)
        {
        case 1:
            tiempo.start();
            jTextField1.setText(String.valueOf(contador));
            break;
        case 2:
             tiempo.start();
            break;
        case 3:
            
        case 4:
            
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D actual = new Transform3D();
        Transform3D inc = new Transform3D();
        Transform3D inc2 = new Transform3D();
        Transform3D inc3 = new Transform3D();

        inc.rotY(-PI / 4);
        inc2.setTranslation(new Vector3d(0f, 0.09f, 1f)); //-0.01f, 0.1f, 0.3f
        inc3.rotX(-PI / 20);

        actual.mul(inc);
        actual.mul(inc2);
        actual.mul(inc3);
        universo.setTransform(actual);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D actual = new Transform3D();
        Transform3D inc = new Transform3D();

        inc.setTranslation(new Vector3d(0.0f, 0.6f, 0.0f));
        actual.mul(inc);
        inc.rotX(-PI / 2);
        actual.mul(inc);

        universo.setTransform(actual);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
  
        Transform3D vista = new Transform3D();
        vista.set(new Vector3d(0, 0.1,0.8));
        universo.setTransform(vista);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void TrasYNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrasYNegActionPerformed
        Transform3D actual = new Transform3D();
        Transform3D inc = new Transform3D();
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        universo.getTransform(actual);
        inc.set(new Vector3d(0, -0.03, 0));
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_TrasYNegActionPerformed

    private void TrasYPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrasYPosActionPerformed
        Transform3D actual = new Transform3D();
        Transform3D inc = new Transform3D();
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        universo.getTransform(actual);
        inc.set(new Vector3d(0, 0.03, 0));
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_TrasYPosActionPerformed

    private void TrasXNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrasXNegActionPerformed
        Transform3D actual = new Transform3D();
        Transform3D inc = new Transform3D();
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        universo.getTransform(actual);
        inc.set(new Vector3d(-0.03, 0, 0));
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_TrasXNegActionPerformed

    private void TrasXPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrasXPosActionPerformed
        TransformGroup universo
        = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0.08, 0, 0));
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_TrasXPosActionPerformed

    private void TrasZNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrasZNegActionPerformed
        TransformGroup universo = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();

        inc.set(new Vector3d(0, 0.0, 0.1));
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_TrasZNegActionPerformed

    private void TrasZPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrasZPosActionPerformed
        TransformGroup universo
        = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D actual = new Transform3D();
        universo.getTransform(actual);
        Transform3D inc = new Transform3D();
        inc.set(new Vector3d(0, 0.0, -0.08));
        actual.mul(inc);
        universo.setTransform(actual);
    }//GEN-LAST:event_TrasZPosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RotXNeg;
    private javax.swing.JButton RotXPos;
    private javax.swing.JToggleButton RotYNeg;
    private javax.swing.JButton RotYPos;
    private javax.swing.JButton RotZNeg;
    private javax.swing.JButton RotZPos;
    private javax.swing.JButton TrasXNeg;
    private javax.swing.JButton TrasXPos;
    private javax.swing.JButton TrasYNeg;
    private javax.swing.JButton TrasYPos;
    private javax.swing.JButton TrasZNeg;
    private javax.swing.JButton TrasZPos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
