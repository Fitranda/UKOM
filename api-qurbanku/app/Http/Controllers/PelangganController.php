<?php

namespace App\Http\Controllers;

use App\Models\Pelanggan;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;

class PelangganController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $data = Pelanggan::all();

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
            'pelanggan'=>'required | alpha_num',
            'telp'=>'required | numeric',
            'email'=>'required | email | unique:pelanggans',
            'password'=>'required | min:8 | regex:/[a-z]/ | regex:/[A-Z]/ | regex:/[0-9]/ | regex:/[@$!%*#?&]/',
            'alamat'=>'required',
            'ttl'=>'required',
            'status'=>'required'
        ]);

        if ($request->hasFile('gambar')) {
            $gambar = $request->file('gambar')->getClientOriginalName();
        $request->file('gambar')->move('upload',$gambar);
        $data = [
            'pelanggan'=>$request->input('pelanggan'),
            'telp'=>$request->input('telp'),
            'gambar'=>url('upload/'.$gambar),
            'email'=>$request->input('email'),
            'password'=>Hash::make($request->input('password')),
            'alamat'=>$request->input('alamat'),
            'ttl'=>$request->input('ttl'),
            'status'=>$request->input('status')
        ];
        } else {
            $data = [
                'pelanggan'=>$request->input('pelanggan'),
                'telp'=>$request->input('telp'),
                'email'=>$request->input('email'),
                'gambar'=>"",
                'password'=>Hash::make($request->input('password')),
                'alamat'=>$request->input('alamat'),
                'ttl'=>$request->input('ttl'),
                'status'=>$request->input('status')
            ];
        }

        $menu = Pelanggan::create($data);

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
     * @param  \App\Models\Pelanggan  $pelanggan
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
        $data = Pelanggan:: where('idpelanggan',$id)->get();
        $pelanggan = Pelanggan:: where('idpelanggan',$id)->value('pelanggan');
        $gambar = Pelanggan:: where('idpelanggan',$id)->value('gambar');
        $email = Pelanggan:: where('idpelanggan',$id)->value('email');
        $telp = Pelanggan:: where('idpelanggan',$id)->value('telp');
        $alamat = Pelanggan:: where('idpelanggan',$id)->value('alamat');
        $ttl = Pelanggan:: where('idpelanggan',$id)->value('ttl');

        return response()->json([
            'data'=>$data,
            'pelanggan'=>$pelanggan,
            'gambar'=>$gambar,
            'email'=>$email,
            'telp'=>$telp,
            'alamat'=>$alamat,
            'ttl'=>$ttl
        ]);
    }

    public function loginp($id,$idp)
    {

        $email = str_replace(',','.',$id);
        $user = Pelanggan::where('email',$email)->first();
        $idpelanggan = Pelanggan::where('email',$email)->value('idpelanggan');
        $emailss =Pelanggan::where('email',$email)->value('email');
        $telp = Pelanggan::where('email',$email)->value('telp');
        $gambar = Pelanggan::where('email',$email)->value('gambar');
        $pelanggan = Pelanggan::where('email',$email)->value('pelanggan');

        if (isset($user)) {
            if ($user->status === 1) {
                if (Hash::check($idp, $user->password)) {

                    return response()->json([
                        'email'=>$emailss,
                        'gambar'=>$gambar,
                        'pelanggan'=>$pelanggan,
                        'telp'=>$telp,
                        'idpelanggan'=>$idpelanggan,
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

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Pelanggan  $pelanggan
     * @return \Illuminate\Http\Response
     */
    public function edit(Pelanggan $pelanggan)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Pelanggan  $pelanggan
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
        $this->validate($request,[
            'pelanggan'=>'required',
            'telp'=>'required | numeric',
            'email'=>'required | email ',
            'password'=>'required',
            'alamat'=>'required',
            'ttl'=>'required',
            'status'=>'required'
        ]);

        if ($request->hasFile('gambar')) {
            $gambar = $request->file('gambar')->getClientOriginalName();
        $request->file('gambar')->move('upload',$gambar);
        $data = [
            'pelanggan'=>$request->input('pelanggan'),
            'telp'=>$request->input('telp'),
            'gambar'=>url('upload/'.$gambar),
            'email'=>$request->input('email'),
            'password'=>Hash::make($request->input('password')),
            'alamat'=>$request->input('alamat'),
            'ttl'=>$request->input('ttl'),
            'status'=>$request->input('status')
        ];
        } else {
            $data = [
                'pelanggan'=>$request->input('pelanggan'),
                'telp'=>$request->input('telp'),
                'email'=>$request->input('email'),
                'password'=>Hash::make($request->input('password')),
                'alamat'=>$request->input('alamat'),
                'ttl'=>$request->input('ttl'),
                'status'=>$request->input('status')
            ];
        }

        // return response()->json($data);

        $menu = Pelanggan::where('idpelanggan',$id)->update($data);
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

        $menu = Pelanggan::where('email',$email)->update($data);
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
     * @param  \App\Models\Pelanggan  $pelanggan
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        $menu = Pelanggan::where('idpelanggan',$id)->delete();

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
        $this->validate($request,[
            'email'=>'required | email ',
            'password'=>'required | min:8 | regex:/[a-z]/ | regex:/[A-Z]/ | regex:/[0-9]/ | regex:/[@$!%*#?&]/'
        ]);

        $email = $request->input('email');
        $password = $request->input('password');

        $user = Pelanggan::where('email',$email)->first();

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

    public function lupa(Request $request)
    {
        $this->validate($request,[
            'pelanggan'=>'required',
            'email'=>'required | email ',
            'password'=>'required | min:8 | regex:/[a-z]/ | regex:/[A-Z]/ | regex:/[0-9]/ | regex:/[@$!%*#?&]/',
            'alamat'=>'required'
        ]);
        //
        $email = $request->input('email');
        $alamat = $request->input('alamat');
        $pelanggan = $request->input('pelanggan');
        $password = Hash::make($request->input('password'));

        $user = Pelanggan::where('email',$email)->where('alamat',$alamat)->where('pelanggan',$pelanggan);

        $data = [
            'password'=>$password
        ];

        $menu = Pelanggan::where('email',$email)->where('alamat',$alamat)->where('pelanggan',$pelanggan)->update($data);
        if ($menu) {
            return response()->json([
                'pesan' => 'Password sudah dirubah !'
            ]);
        }else {
            return response()->json([
                'pesan' => 'Email atau alamat atau nama salah !'
            ]);
        }
    }
}
