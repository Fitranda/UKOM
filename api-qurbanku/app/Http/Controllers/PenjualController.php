<?php

namespace App\Http\Controllers;

use App\Models\Penjual;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class PenjualController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $data = Penjual::all();

        return response()->json($data);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create(Request $request)
    {
        //
        $this->validate($request,[
            'penjual'=>'required',
            'telp'=>'required | numeric',
            'email'=>'required | email | unique:penjuals',
            'password'=>'required',
            'alamat'=>'required',
            'link'=>'required',
            'status'=>'required',
            'ratings'=>'required'
        ]);

        if ($request->hasFile('gambar')) {
            $gambar = $request->file('gambar')->getClientOriginalName();
        $request->file('gambar')->move('upload',$gambar);
        $data = [
            'penjual'=>$request->input('penjual'),
            'telp'=>$request->input('telp'),
            'gambar'=>url('upload/'.$gambar),
            'email'=>$request->input('email'),
            'password'=>Hash::make($request->input('password')),
            'alamat'=>$request->input('alamat'),
            'link'=>$request->input('link'),
            'status'=>$request->input('status'),
            'ratings'=>$request->input('ratings')
        ];
        } else {
            $data = [
                'penjual'=>$request->input('penjual'),
                'telp'=>$request->input('telp'),
                'email'=>$request->input('email'),
                'gambar'=>"",
                'password'=>Hash::make($request->input('password')),
                'alamat'=>$request->input('alamat'),
                'link'=>$request->input('link'),
                'status'=>$request->input('status'),
                'ratings'=>$request->input('ratings')
            ];
        }

        $menu = Penjual::create($data);

        if ($menu) {
            return response()->json([
                'pesan' => 'Data Sudah Disimpan'
            ]);
        }
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Penjual  $penjual
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
        $data = Penjual:: where('idPenjual',$id)->get();
        $Penjual = Penjual:: where('idPenjual',$id)->value('Penjual');
        $gambar = Penjual:: where('idPenjual',$id)->value('gambar');
        $email = Penjual:: where('idPenjual',$id)->value('email');
        $telp = Penjual:: where('idPenjual',$id)->value('telp');
        $alamat = Penjual:: where('idPenjual',$id)->value('alamat');
        $link = Penjual:: where('idPenjual',$id)->value('link');

        return response()->json([
            'data'=>$data,
            'penjual'=>$Penjual,
            'gambar'=>$gambar,
            'email'=>$email,
            'telp'=>$telp,
            'alamat'=>$alamat,
            'link'=>$link
        ]);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Penjual  $penjual
     * @return \Illuminate\Http\Response
     */
    public function edit(Penjual $penjual)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Penjual  $penjual
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
        $this->validate($request,[
            'penjual'=>'required',
            'telp'=>'required | numeric',
            'email'=>'required | email ',
            'password'=>'required',
            'alamat'=>'required',
            'link'=>'required',
            'status'=>'required'
        ]);

        if ($request->hasFile('gambar')) {
            $gambar = $request->file('gambar')->getClientOriginalName();
        $request->file('gambar')->move('upload',$gambar);
        $data = [
            'penjual'=>$request->input('penjual'),
            'telp'=>$request->input('telp'),
            'gambar'=>url('upload/'.$gambar),
            'email'=>$request->input('email'),
            'password'=>Hash::make($request->input('password')),
            'alamat'=>$request->input('alamat'),
            'link'=>$request->input('link'),
            'status'=>$request->input('status')
        ];
        } else {
            $data = [
                'penjual'=>$request->input('penjual'),
                'telp'=>$request->input('telp'),
                'email'=>$request->input('email'),
                'password'=>Hash::make($request->input('password')),
                'alamat'=>$request->input('alamat'),
                'link'=>$request->input('link'),
                'status'=>$request->input('status')
            ];
        }

        // return response()->json($data);

        $menu = Penjual::where('idpenjual',$id)->update($data);
        if ($menu) {
            return response()->json([
                'pesan' => 'Data Sudah Diubah !'
            ]);
        }
    }


    public function updategambar(Request $request, $id)
    {
        //
        $this->validate($request,[
            'gambar'=>'required'
        ]);

        $email = str_replace(',','.',$id);
        $gambar = $request->file('gambar')->getClientOriginalName();
        $request->file('gambar')->move('upload',$gambar);
        $data = [
            'gambar'=>url('upload/'.$gambar)
        ];

        // return response()->json($data);

        $menu = Penjual::where('email',$email)->update($data);
        if ($menu) {
            return response()->json([
                'gambar'=>url('upload/'.$gambar),
                'pesan' => 'Data Sudah Disimpan !'
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Penjual  $penjual
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        $menu = Penjual::where('idpenjual',$id)->delete();

        if ($menu) {
            return response()->json([
                'pesan' => 'data sudah dihapus'
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Admin  $admin
     * @return \Illuminate\Http\Response
     */
    public function login(Request $request)
    {
        //
        $email = $request->input('email');
        $password = $request->input('password');

        $user = Penjual::where('email',$email)->first();

        if (isset($user)) {
            if ($user->status === 1) {
                if (Hash::check($password, $user->password)) {

                    return response()->json([
                        'pesan'=> 'login berhasil',
                        'data'=> $user
                    ]);
                }else {
                    return response()->json([
                        'pesan'=> 'login gagal , Password salah',
                        'data'=> ''
                    ]);
                }
            }else {
                    return response()->json([
                        'pesan'=> 'login gagal , akun anda diblokir',
                        'data'=> ''
                    ]);
                }
        } else {
            return response()->json([
                'pesan'=> 'login gagal , email salah',
                'data'=> ''
            ]);
        }
    }

    public function loginp($id,$idp)
    {

        $email = str_replace(',','.',$id);
        $user = penjual::where('email',$email)->first();
        $idpenjual = penjual::where('email',$email)->value('idpenjual');
        $emailss =penjual::where('email',$email)->value('email');
        $telp = penjual::where('email',$email)->value('telp');
        $gambar = penjual::where('email',$email)->value('gambar');
        $penjual = penjual::where('email',$email)->value('penjual');

        if (isset($user)) {
            if ($user->status === 1) {
                if (Hash::check($idp, $user->password)) {

                    return response()->json([
                        'email'=>$emailss,
                        'gambar'=>$gambar,
                        'penjual'=>$penjual,
                        'telp'=>$telp,
                        'idpenjual'=>$idpenjual,
                        'pesan'=> 'login berhasil',
                        'data'=> $user
                    ]);
                }else {
                    return response()->json([
                        'pesan'=> 'login gagal , Password salah',
                        'data'=> ''
                    ]);
                }
            }else {
                    return response()->json([
                        'pesan'=> 'login gagal , akun anda diblokir',
                        'data'=> ''
                    ]);
                }
        } else {
            return response()->json([
                'pesan'=> 'login gagal , email salah',
                'data'=> ''
            ]);
        }
    }
}
