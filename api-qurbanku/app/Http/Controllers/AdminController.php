<?php

namespace App\Http\Controllers;

use App\Models\Admin;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class AdminController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $data = Admin::all();

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
            'admin'=>'required',
            'telp'=>'required | numeric',
            'email'=>'required | email | unique:pelanggans',
            'password'=>'required',
            'alamat'=>'required',
            'username'=>'required',
            'status'=>'required'
        ]);

        $data = [
            'admin'=>$request->input('admin'),
            'telp'=>$request->input('telp'),
            'email'=>$request->input('email'),
            'password'=>Hash::make($request->input('password')),
            'alamat'=>$request->input('alamat'),
            'username'=>$request->input('username'),
            'status'=>$request->input('status')
        ];

        $menu = Admin::create($data);

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
     * @param  \App\Models\Admin  $admin
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
        $data = Admin:: where('idadmin',$id)->get();

        return response()->json($data);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Admin  $admin
     * @return \Illuminate\Http\Response
     */
    public function edit(Admin $admin)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Admin  $admin
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
        $this->validate($request,[
            'admin'=>'required',
            'telp'=>'required | numeric',
            'email'=>'required | email',
            'password'=>'required',
            'alamat'=>'required',
            'username'=>'required',
            'status'=>'required'
        ]);

        $data = [
            'admin'=>$request->input('admin'),
            'telp'=>$request->input('telp'),
            'email'=>$request->input('email'),
            'password'=>Hash::make($request->input('password')),
            'alamat'=>$request->input('alamat'),
            'username'=>$request->input('username'),
            'status'=>$request->input('status')
        ];

        $menu = Admin::where('idadmin',$id)->update($data);
        if ($menu) {
            return response()->json([
                'pesan' => 'Data Sudah Diubah !'
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Admin  $admin
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        $menu = Admin::where('idadmin',$id)->delete();

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
        $username = $request->input('username');
        $password = $request->input('password');

        $user = Admin::where('username',$username)->first();

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
                'pesan'=> 'login gagal , Username salah',
                'data'=> ''
            ]);
        }
    }
}
