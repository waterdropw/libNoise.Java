%module NoiseUtils


%import "noise.i"
%import "model.i"
%import "module.i"

%pragma(java) jniclassimports=%{
import net.xbwee.libnoise.module.Module;
%}
%typemap(javaimports) SWIGTYPE %{
import net.xbwee.libnoise.module.Module;
%}

%include "std_string.i"
%include "enums.swg"
%javaconst(1);


%{
    #include "noiseutils/noiseutils.h"
%}

%include "noiseutils/noiseutils.h"


