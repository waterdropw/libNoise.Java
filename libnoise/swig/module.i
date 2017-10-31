%module NoiseModule


%import "noise.i"

%pragma(java) jniclassimports=%{
import net.xbwee.libnoise.noise.NoiseQuality;
%}
%typemap(javaimports) SWIGTYPE %{
import net.xbwee.libnoise.noise.NoiseQuality;
%}

SWIG_JAVABODY_PROXY(public, public, SWIGTYPE)
SWIG_JAVABODY_TYPEWRAPPER(public, public, public, SWIGTYPE)

%include "enums.swg"
%javaconst(1);

%{  
    #include "noise/module/modulebase.h"
    #include "noise/module/add.h"
    #include "noise/module/abs.h"
    #include "noise/module/billow.h"
    #include "noise/module/blend.h"
    #include "noise/module/cache.h"
    #include "noise/module/checkerboard.h"
    #include "noise/module/clamp.h"
    #include "noise/module/const.h"
    #include "noise/module/curve.h"
    #include "noise/module/cylinders.h"
    #include "noise/module/displace.h"
    #include "noise/module/exponent.h"
    #include "noise/module/invert.h"
    #include "noise/module/max.h"
    #include "noise/module/min.h"
    #include "noise/module/multiply.h"
    #include "noise/module/perlin.h"
    #include "noise/module/power.h"
    #include "noise/module/ridgedmulti.h"
    #include "noise/module/rotatepoint.h"
    #include "noise/module/scalebias.h"
    #include "noise/module/scalepoint.h"
    #include "noise/module/select.h"
    #include "noise/module/spheres.h"
    #include "noise/module/terrace.h"
    #include "noise/module/translatepoint.h"
    #include "noise/module/turbulence.h"
    #include "noise/module/voronoi.h"
%}

%include "noise/module/modulebase.h"
%include "noise/module/add.h"
%include "noise/module/abs.h"
%include "noise/module/billow.h"
%include "noise/module/blend.h"
%include "noise/module/cache.h"
%include "noise/module/checkerboard.h"
%include "noise/module/clamp.h"
%include "noise/module/const.h"
%include "noise/module/curve.h"
%include "noise/module/cylinders.h"
%include "noise/module/displace.h"
%include "noise/module/exponent.h"
%include "noise/module/invert.h"
%include "noise/module/max.h"
%include "noise/module/min.h"
%include "noise/module/multiply.h"
%include "noise/module/perlin.h"
%include "noise/module/power.h"
%include "noise/module/ridgedmulti.h"
%include "noise/module/rotatepoint.h"
%include "noise/module/scalebias.h"
%include "noise/module/scalepoint.h"
%include "noise/module/select.h"
%include "noise/module/spheres.h"
%include "noise/module/terrace.h"
%include "noise/module/translatepoint.h"
%include "noise/module/turbulence.h"
%include "noise/module/voronoi.h"